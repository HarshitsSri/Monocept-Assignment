package com.swabhav.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swabhav.demo.dto.DepartmentRequestDto;
import com.swabhav.demo.dto.DepartmentResponseDto;
import com.swabhav.demo.dto.EmployeeRequestDto;
import com.swabhav.demo.dto.EmployeeResponseDto;
import com.swabhav.demo.dto.PageResponseDto;
import com.swabhav.demo.exception.DuplicateResourceException;
import com.swabhav.demo.exception.ResourceNotFoundException;
import com.swabhav.demo.model.Department;
import com.swabhav.demo.model.Employee;
import com.swabhav.demo.repository.DepartmentRepository;
import com.swabhav.demo.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;

	@Override
	public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {
		log.info("Create operation started for departmentName={}", requestDto.getDepartmentName());

		if (departmentRepository.existsByDepartmentName(requestDto.getDepartmentName())) {
			log.warn("Duplicate data: department name already exists={}", requestDto.getDepartmentName());
			throw new DuplicateResourceException("Department name already exists: " + requestDto.getDepartmentName());
		}

		validateEmployeeEmailsForCreate(requestDto.getEmployees());

		Department department = modelMapper.map(requestDto, Department.class);
		department.setId(null);

		List<Employee> employees = attachEmployeesToDepartment(department, requestDto.getEmployees());
		department.setEmployees(employees);

		Department savedDepartment = departmentRepository.save(department);

		log.info("Create operation completed. departmentId={}", savedDepartment.getId());
		return mapToDepartmentResponseDto(savedDepartment);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DepartmentResponseDto> getAllDepartments() {
		log.info("Read operation started: getAllDepartments");

		List<DepartmentResponseDto> departments = departmentRepository.findAll().stream()
				.map(this::mapToDepartmentResponseDto).toList();

		log.info("Read operation completed. totalDepartments={}", departments.size());
		return departments;
	}

	@Override
	@Transactional(readOnly = true)
	public PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(int pageNumber, int pageSize) {
		log.info("Read operation started: pagination pageNumber={}, pageSize={}", pageNumber, pageSize);

		validatePagination(pageNumber, pageSize);

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
		var page = departmentRepository.findAll(pageable);

		List<DepartmentResponseDto> content = page.getContent().stream().map(this::mapToDepartmentResponseDto).toList();

		log.info("Read operation completed: currentPage={}, totalPages={}", page.getNumber(), page.getTotalPages());

		return new PageResponseDto<>(content, page.getNumber(), page.getSize(), page.getTotalElements(),
				page.getTotalPages(), page.isLast());
	}

	@Override
	@Transactional(readOnly = true)
	public DepartmentResponseDto getDepartmentById(Long id) {
		log.info("Read operation started: getDepartmentById id={}", id);

		Department department = findDepartmentById(id);

		log.info("Read operation completed: departmentId={}", id);
		return mapToDepartmentResponseDto(department);
	}

	@Override
	public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto requestDto) {
		log.info("Update operation started. departmentId={}", id);

		Department existingDepartment = findDepartmentById(id);

		if (!existingDepartment.getDepartmentName().equals(requestDto.getDepartmentName())
				&& departmentRepository.existsByDepartmentNameAndIdNot(requestDto.getDepartmentName(), id)) {
			log.warn("Duplicate data during update. departmentName={}", requestDto.getDepartmentName());
			throw new DuplicateResourceException("Department name already exists: " + requestDto.getDepartmentName());
		}

		validateEmployeeEmailsForUpdate(existingDepartment, requestDto.getEmployees());

		modelMapper.map(requestDto, existingDepartment);
		existingDepartment.setId(id);

		List<Employee> updatedEmployees = new ArrayList<>();
		for (EmployeeRequestDto employeeRequestDto : requestDto.getEmployees()) {
			Employee employee = new Employee();
			modelMapper.map(employeeRequestDto, employee);
			employee.setDepartment(existingDepartment);
			updatedEmployees.add(employee);
		}

		existingDepartment.getEmployees().clear();
		existingDepartment.getEmployees().addAll(updatedEmployees);

		Department savedDepartment = departmentRepository.save(existingDepartment);

		log.info("Update operation completed. departmentId={}", savedDepartment.getId());
		return mapToDepartmentResponseDto(savedDepartment);
	}

	@Override
	public void deleteDepartment(Long id) {
		log.info("Delete operation started. departmentId={}", id);

		Department department = findDepartmentById(id);
		departmentRepository.delete(department);

		log.info("Delete operation completed. departmentId={}", id);
	}

	private Department findDepartmentById(Long id) {
		return departmentRepository.findById(id).orElseThrow(() -> {
			log.warn("Resource not found. departmentId={}", id);
			return new ResourceNotFoundException("Department not found with id: " + id);
		});
	}

	private List<Employee> attachEmployeesToDepartment(Department department,
			List<EmployeeRequestDto> employeeRequestDtos) {
		List<Employee> employees = new ArrayList<>();

		for (EmployeeRequestDto employeeRequestDto : employeeRequestDtos) {
			Employee employee = modelMapper.map(employeeRequestDto, Employee.class);
			employee.setDepartment(department);
			employees.add(employee);
		}

		return employees;
	}

	private void validateEmployeeEmailsForCreate(List<EmployeeRequestDto> employeeRequestDtos) {
		if (employeeRequestDtos == null || employeeRequestDtos.isEmpty()) {
			throw new IllegalArgumentException("At least one employee is required");
		}

		Set<String> seenEmails = new HashSet<>();

		for (EmployeeRequestDto employeeRequestDto : employeeRequestDtos) {
			String email = employeeRequestDto.getEmail();

			if (!seenEmails.add(email)) {
				log.warn("Duplicate data found in request: duplicate employee email={}", email);
				throw new DuplicateResourceException("Duplicate employee email in request: " + email);
			}

			if (employeeRepository.existsByEmail(email)) {
				log.warn("Duplicate data: employee email already exists={}", email);
				throw new DuplicateResourceException("Employee email already exists: " + email);
			}
		}
	}

	private void validateEmployeeEmailsForUpdate(Department existingDepartment,
			List<EmployeeRequestDto> employeeRequestDtos) {
		if (employeeRequestDtos == null || employeeRequestDtos.isEmpty()) {
			throw new IllegalArgumentException("At least one employee is required");
		}

		Set<String> seenEmails = new HashSet<>();
		Set<String> existingEmails = existingDepartment.getEmployees() == null ? Set.of()
				: existingDepartment.getEmployees().stream().map(Employee::getEmail).collect(Collectors.toSet());

		for (EmployeeRequestDto employeeRequestDto : employeeRequestDtos) {
			String email = employeeRequestDto.getEmail();

			if (!seenEmails.add(email)) {
				log.warn("Duplicate data found in update request: duplicate employee email={}", email);
				throw new DuplicateResourceException("Duplicate employee email in request: " + email);
			}

			if (!existingEmails.contains(email) && employeeRepository.existsByEmail(email)) {
				log.warn("Duplicate data: employee email already exists outside current department={}", email);
				throw new DuplicateResourceException("Employee email already exists: " + email);
			}
		}
	}

	private void validatePagination(int pageNumber, int pageSize) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("pageNumber must not be negative");
		}
		if (pageSize <= 0) {
			throw new IllegalArgumentException("pageSize must be greater than 0");
		}
		if (pageSize > 100) {
			throw new IllegalArgumentException("pageSize must not be greater than 100");
		}
	}

	private DepartmentResponseDto mapToDepartmentResponseDto(Department department) {
		DepartmentResponseDto responseDto = modelMapper.map(department, DepartmentResponseDto.class);

		if (department.getEmployees() != null) {
			List<EmployeeResponseDto> employeeResponseDtos = department.getEmployees().stream()
					.map(employee -> modelMapper.map(employee, EmployeeResponseDto.class)).toList();
			responseDto.setEmployees(employeeResponseDtos);
		}

		return responseDto;
	}
}
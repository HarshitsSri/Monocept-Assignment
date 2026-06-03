package com.swabhav.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swabhav.demo.dto.DepartmentRequestDto;
import com.swabhav.demo.dto.DepartmentResponseDto;
import com.swabhav.demo.dto.PageResponseDto;
import com.swabhav.demo.service.DepartmentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
@Slf4j
public class DepartmentController {

	private final DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<DepartmentResponseDto> createDepartment(@Valid @RequestBody DepartmentRequestDto requestDto) {

		log.info("API request received: CREATE department, departmentName={}", requestDto.getDepartmentName());

		DepartmentResponseDto response = departmentService.createDepartment(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {

		log.info("API request received: GET all departments");

		return ResponseEntity.ok(departmentService.getAllDepartments());
	}

	@GetMapping("/page")
	public ResponseEntity<PageResponseDto<DepartmentResponseDto>> getAllDepartmentsWithPagination(
			@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {

		log.info("API request received: GET departments with pagination, pageNumber={}, pageSize={}", pageNumber,
				pageSize);

		return ResponseEntity.ok(departmentService.getAllDepartmentsWithPagination(pageNumber, pageSize));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable Long id) {

		log.info("API request received: GET department by id={}", id);

		return ResponseEntity.ok(departmentService.getDepartmentById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<DepartmentResponseDto> updateDepartment(@PathVariable Long id,
			@Valid @RequestBody DepartmentRequestDto requestDto) {

		log.info("API request received: UPDATE department id={}, departmentName={}", id,
				requestDto.getDepartmentName());

		return ResponseEntity.ok(departmentService.updateDepartment(id, requestDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {

		log.info("API request received: DELETE department id={}", id);

		departmentService.deleteDepartment(id);
		return ResponseEntity.noContent().build();
	}
}
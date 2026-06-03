package com.swabhav.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swabhav.demo.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Create ke time duplicate department name check
    boolean existsByDepartmentName(String departmentName);

    // Update ke time duplicate department name check
    boolean existsByDepartmentNameAndIdNot(String departmentName, Long id);
}

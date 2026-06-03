package com.swabhav.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swabhav.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Create ke time duplicate email check
    boolean existsByEmail(String email);

    // Update ke time duplicate email check
    boolean existsByEmailAndIdNot(String email, Long id);
}

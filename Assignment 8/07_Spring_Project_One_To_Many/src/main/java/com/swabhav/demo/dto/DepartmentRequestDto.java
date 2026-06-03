package com.swabhav.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DepartmentRequestDto {

    @JsonProperty("department_name")
    @NotBlank(message = "Department name is required")
    private String departmentName;

    @NotBlank(message = "Location is required")
    private String location;

    @Valid
    @NotEmpty(message = "At least one employee is required")
    private List<EmployeeRequestDto> employees;
}
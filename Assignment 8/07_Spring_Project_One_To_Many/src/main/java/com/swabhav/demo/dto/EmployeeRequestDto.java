package com.swabhav.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EmployeeRequestDto {

    @JsonProperty("employee_name")
    @NotBlank(message = "Employee name is required")
    private String employeeName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be greater than zero")
    private Double salary;
}
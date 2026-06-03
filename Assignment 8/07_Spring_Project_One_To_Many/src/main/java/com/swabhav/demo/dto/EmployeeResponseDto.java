package com.swabhav.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeResponseDto {

    private Long id;

    @JsonProperty("employee_name")
    private String employeeName;

    private String email;

    private Double salary;
}
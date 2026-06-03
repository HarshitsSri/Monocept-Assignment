package com.swabhav.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class DepartmentResponseDto {

    private Long id;

    @JsonProperty("department_name")
    private String departmentName;

    private String location;

    private List<EmployeeResponseDto> employees;
}

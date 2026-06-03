package com.swabhav.demo.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApiErrorResponse {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
}

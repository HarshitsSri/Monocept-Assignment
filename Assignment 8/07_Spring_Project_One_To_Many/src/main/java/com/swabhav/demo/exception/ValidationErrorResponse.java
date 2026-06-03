package com.swabhav.demo.exception;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ValidationErrorResponse {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private List<String> messages;
}

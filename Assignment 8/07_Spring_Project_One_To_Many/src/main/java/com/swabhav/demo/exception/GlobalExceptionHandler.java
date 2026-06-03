package com.swabhav.demo.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
		log.warn("Resource not found: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
	}

	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ApiErrorResponse> handleDuplicateResource(DuplicateResourceException ex) {
		log.warn("Duplicate data: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(buildResponse(HttpStatus.CONFLICT, ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(this::formatFieldError)
				.collect(Collectors.toList());

		log.warn("Validation failure: {}", messages);

		ValidationErrorResponse response = ValidationErrorResponse.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value()).error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.messages(messages).build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		log.warn("Invalid path variable or request parameter: {}", ex.getName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(buildResponse(HttpStatus.BAD_REQUEST, "Invalid value for parameter: " + ex.getName()));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidJson(HttpMessageNotReadableException ex) {
		log.warn("Invalid request body: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(buildResponse(HttpStatus.BAD_REQUEST, "Invalid request body"));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex) {
		log.warn("Database constraint violation: {}", ex.getMostSpecificCause().getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(buildResponse(HttpStatus.CONFLICT, "Database constraint violation"));
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiErrorResponse> handleAccessDenied(AccessDeniedException ex) {
		log.warn("Access denied: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(buildResponse(HttpStatus.FORBIDDEN, "Access denied"));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
		log.warn("Validation failure: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex) {
		log.error("Unexpected error", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred"));
	}

	private ApiErrorResponse buildResponse(HttpStatus status, String message) {
		return ApiErrorResponse.builder().timestamp(LocalDateTime.now()).status(status.value())
				.error(status.getReasonPhrase()).message(message).build();
	}

	private String formatFieldError(FieldError error) {
		return error.getField() + " : " + error.getDefaultMessage();
	}
}
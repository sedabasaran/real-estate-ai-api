package com.realestate.finder.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PropertyNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handlePropertyNotFoundException(PropertyNotFoundException ex,
			HttpServletRequest request) {

		return new ResponseEntity<>(buildErrorBody(ex.getMessage(), HttpStatus.NOT_FOUND, request),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Map<String, Object>> handleBadRequest(BadRequestException ex, HttpServletRequest request) {

		return new ResponseEntity<>(buildErrorBody(ex.getMessage(), HttpStatus.BAD_REQUEST, request),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		Map<String, Object> body = buildErrorBody("Validation failed", HttpStatus.BAD_REQUEST, request);
		Map<String, String> validationErrors = new HashMap<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			validationErrors.put(error.getField(), error.getDefaultMessage());
		}

		body.put("validationErrors", validationErrors);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex, HttpServletRequest request) {
		return new ResponseEntity<>(
				buildErrorBody("Sunucu hatası: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private Map<String, Object> buildErrorBody(String message, HttpStatus status, HttpServletRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());
		body.put("error", status.getReasonPhrase());
		body.put("message", message);
		body.put("path", request.getRequestURI());
		return body;
	}
}

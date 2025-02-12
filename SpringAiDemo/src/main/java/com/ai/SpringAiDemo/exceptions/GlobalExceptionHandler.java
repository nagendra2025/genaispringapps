package com.ai.SpringAiDemo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		
		
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)  ->  {
			
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object>  handleConstraintViolationException(ConstraintViolationException ex) {
		
		Map<String, String> errors = new HashMap<>();
		
		ex.getConstraintViolations().forEach(  (violation)  ->  {
			
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			
			errors.put(propertyPath, message);
			
		});
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
	}

}

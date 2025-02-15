package com.project.demo.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.demo.exception.ProjectNotFound;
import com.project.demo.exception.ResourceNotFound;

@RestControllerAdvice

public class GlobalExceptionHandler {
	
    // Method to handle ProjectNotFound exceptions
	@ExceptionHandler(ProjectNotFound.class)
	ResponseEntity<String> handleProjetNotFound(ProjectNotFound ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(ResourceNotFound.class)
	ResponseEntity<String> handleResouceNotFound(ResourceNotFound ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		
	}
	
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException ex){
		
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		
	} 
}

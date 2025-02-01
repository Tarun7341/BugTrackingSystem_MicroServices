package com.project.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.demo.exception.ProjectNotFound;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProjectNotFound.class)
	ResponseEntity<String> handleResouceNotFound(ProjectNotFound ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
		
	}
}

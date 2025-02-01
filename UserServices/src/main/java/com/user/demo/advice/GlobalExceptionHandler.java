package com.user.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.user.demo.exception.UserNotFound;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFound.class)
	ResponseEntity<String> handleResourceNotFoundException(UserNotFound ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

}

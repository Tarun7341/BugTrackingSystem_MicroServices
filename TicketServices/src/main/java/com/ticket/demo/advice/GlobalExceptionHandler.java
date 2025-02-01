package com.ticket.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ticket.demo.exception.TicketNotFound;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(TicketNotFound.class)
	ResponseEntity<String> HandleResourceNotFound(TicketNotFound ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

}

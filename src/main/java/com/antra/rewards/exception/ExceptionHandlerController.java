package com.antra.rewards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> handleAllExceptions(Exception ex, RequestBody request) {
		return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(CustomerNotFoundException ex) {
		return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
	}
}

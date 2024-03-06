package com.hexaware.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason= "this Customer does not exists")
	@ExceptionHandler({CustomerNotFoundException.class, LoanApplicationNotFoundException.class
		, LoanTypeNotFoundException.class
		, LoanStatusNotFoundException.class, LoanTypeNameNotFoundException.class})
	public ResponseEntity<String> NotFound(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({CustomerCredentialsAlreadyExists.class, AdminCredentialsAlreadyExists.class
		, PropertyCredentialsAlreadyExists.class, LoanTypeAlreadyExists.class })
	public ResponseEntity<String> alreadyExists(Exception e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	
}

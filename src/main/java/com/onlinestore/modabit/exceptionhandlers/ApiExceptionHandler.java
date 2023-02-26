package com.onlinestore.modabit.exceptionhandlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.onlinestore.modabit.exceptions.CartShoppingException;
import com.onlinestore.modabit.exceptions.NoProductElementException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(CartShoppingException.class)
	public ResponseEntity<ProblemResponse> handlerCartShoppingException(CartShoppingException ex){
		
		ProblemResponse response = new ProblemResponse();
		
		response.setTimestemp(LocalDateTime.now());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessenger(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoProductElementException.class)
	public ResponseEntity<ProblemResponse> handlerNoProductElementException(NoProductElementException ex){
		
		ProblemResponse response = new ProblemResponse();
		
		response.setTimestemp(LocalDateTime.now());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessenger(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}

}

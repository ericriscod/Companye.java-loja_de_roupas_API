package com.onlinestore.modabit.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.onlinestore.modabit.exceptions.CartShoppingException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(CartShoppingException.class)
	public ResponseEntity<ProblemResponse> handlerCartShoppingException(CartShoppingException ex){
		
		ProblemResponse response = new ProblemResponse();
		
		response.setCode(HttpStatus.BAD_REQUEST.value());
		response.setMessenger(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}

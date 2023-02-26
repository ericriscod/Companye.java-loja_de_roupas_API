package com.onlinestore.modabit.exceptionhandlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.onlinestore.modabit.exceptions.ProductArgumentsException;
import com.onlinestore.modabit.exceptions.NoProductElementException;
import com.onlinestore.modabit.exceptions.StockException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(ProductArgumentsException.class)
	public ResponseEntity<ProblemResponse> handlerCartShoppingException(ProductArgumentsException ex){
		
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
	
	@ExceptionHandler(StockException.class)
	public ResponseEntity<ProblemResponse> handlerStocckException(StockException ex){
		
		ProblemResponse response = new ProblemResponse();
		
		response.setTimestemp(LocalDateTime.now());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessenger(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}

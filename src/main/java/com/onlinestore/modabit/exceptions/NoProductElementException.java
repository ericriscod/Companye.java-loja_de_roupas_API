package com.onlinestore.modabit.exceptions;

public class NoProductElementException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public NoProductElementException(String msg) {
		super(msg);
	}

}

package com.capgemini.customer.exceptions;

public class CustomerNotFoundException extends RuntimeException {

	
	public CustomerNotFoundException(String message) {
		super(message);
	}
}

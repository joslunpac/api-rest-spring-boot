package com.apirestspringboot.exception;

public class ApiBadRequestException extends RuntimeException {

	public ApiBadRequestException(String message) {
		super(message);
	}

}

package com.gnostrenoff.cdb.controllers.exceptions;

public class ConnectionException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ConnectionException(String message) {
		super(message);
	}
	
}

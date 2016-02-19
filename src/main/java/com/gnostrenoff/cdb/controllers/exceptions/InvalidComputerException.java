package com.gnostrenoff.cdb.controllers.exceptions;

/**
 * Exception thrown when dto computer has not valid data
 * 
 * @author excilys
 */
public class InvalidComputerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidComputerException(String message) {
		super(message);
	}

}

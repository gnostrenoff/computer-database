package com.gnostrenoff.cdb.services.exceptions;

/**
 * Exception thrown when properties files is not found while configuring
 * connection
 * 
 * @author excilys
 */
public class ComputerValidatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ComputerValidatorException(String message) {
		super(message);
	}

}

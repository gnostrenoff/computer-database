package com.gnostrenoff.cdb.dao.exceptions;

/**
 * Exception thrown when properties files is not found while configuring
 * connection
 * 
 * @author excilys
 */
public class ConnectionPropertiesFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConnectionPropertiesFileNotFoundException(String message) {
		super(message);
	}

}

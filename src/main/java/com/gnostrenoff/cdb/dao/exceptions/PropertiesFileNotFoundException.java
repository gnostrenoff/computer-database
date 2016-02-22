package com.gnostrenoff.cdb.dao.exceptions;

/**
 * Exception thrown when properties files is not found while configuring
 * connection
 * 
 * @author excilys
 */
public class PropertiesFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PropertiesFileNotFoundException(String message) {
		super(message);
	}

}

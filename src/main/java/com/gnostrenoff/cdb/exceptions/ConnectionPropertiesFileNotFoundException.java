package com.gnostrenoff.cdb.exceptions;

/**
 * Exception thrown when properties files is not found while configuring connection
 * @author excilys
 */
public class ConnectionPropertiesFileNotFoundException extends RuntimeException {
	
	public ConnectionPropertiesFileNotFoundException(String message){
		super(message);
	}

}

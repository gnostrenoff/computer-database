package com.gnostrenoff.cdb.exceptions;

/**
 * Exception to notify that the dates of a computer are not correct
 * @author excilys
 */
public class DatesNotCorrectException extends RuntimeException{
	
	public DatesNotCorrectException(String message){
		super(message);
	}

}

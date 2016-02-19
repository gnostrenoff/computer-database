package com.gnostrenoff.cdb.dao.exceptions;

/**
 * generic exception for dao special behevior
 * 
 * @author excilys
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException(String message) {
		super(message);
	}

}

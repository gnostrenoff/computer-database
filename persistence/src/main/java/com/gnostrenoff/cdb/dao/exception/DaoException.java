package com.gnostrenoff.cdb.dao.exception;

/**
 * generic exception for dao special behevior.
 *
 * @author excilys
 */
public class DaoException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new dao exception.
   *
   * @param message
   *          the message
   */
  public DaoException(String message) {
    super(message);
  }
  
  public DaoException(String message, Throwable cause) {
    super(message, cause);
  }

}

package com.gnostrenoff.cdb.controller.exception;

/**
 * Exception thrown when dto computer has not valid data.
 *
 * @author excilys
 */
public class InvalidComputerException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new invalid computer exception.
   *
   * @param message the message
   */
  public InvalidComputerException(String message) {
    super(message);
  }

  public InvalidComputerException(String message, Throwable cause) {
    super(message, cause);
  }
  
}

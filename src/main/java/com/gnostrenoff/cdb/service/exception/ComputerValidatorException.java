package com.gnostrenoff.cdb.service.exception;

/**
 * Exception thrown when properties files is not found while configuring connection.
 *
 * @author excilys
 */
public class ComputerValidatorException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new computer validator exception.
   *
   * @param message the message
   */
  public ComputerValidatorException(String message) {
    super(message);
  }
  
  public ComputerValidatorException(String message, Throwable cause) {
    super(message, cause);
  }

}

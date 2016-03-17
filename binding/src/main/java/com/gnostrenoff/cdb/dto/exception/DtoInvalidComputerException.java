package com.gnostrenoff.cdb.dto.exception;

/**
 * Exception thrown when dto computer has not valid data.
 *
 * @author excilys
 */
public class DtoInvalidComputerException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new invalid computer exception.
   *
   * @param message the message
   */
  public DtoInvalidComputerException(String message) {
    super(message);
  }

  public DtoInvalidComputerException(String message, Throwable cause) {
    super(message, cause);
  }
  
}

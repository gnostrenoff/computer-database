package com.gnostrenoff.cdb.controller.exception;

/**
 * The Class ConnectionException.
 */
public class ConnectionException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new connection exception.
   *
   * @param message
   *          the message
   */
  public ConnectionException(String message) {
    super(message);
  }

  public ConnectionException(String message, Throwable cause) {
    super(message, cause);
  }

}

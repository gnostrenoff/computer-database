package com.gnostrenoff.cdb.dao.exception;

/**
 * The Class TransactionException.
 */
public class TransactionException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new transaction exception.
   *
   * @param message
   *          the message
   */
  public TransactionException(String message) {
    super(message);
  }

  public TransactionException(String message, Throwable cause) {
    super(message, cause);
  }

}

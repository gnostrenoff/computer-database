package com.gnostrenoff.cdb.service.exception;

/**
 * The Class TransactionException.
 */
public class ServiceTransactionException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new transaction exception.
   *
   * @param message the message
   */
  public ServiceTransactionException(String message) {
    super(message);
  }
  
  public ServiceTransactionException(String message, Throwable cause) {
    super(message, cause);
  }
}

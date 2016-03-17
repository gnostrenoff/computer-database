package com.gnostrenoff.cdb.controller.exception;

public class InvalidRequestException extends RuntimeException {

  private static final long serialVersionUID = -496208090897575830L;

  public InvalidRequestException(String message) {
    super(message);
  }

  public InvalidRequestException(String message, Throwable cause) {
    super(message, cause);
  }

}

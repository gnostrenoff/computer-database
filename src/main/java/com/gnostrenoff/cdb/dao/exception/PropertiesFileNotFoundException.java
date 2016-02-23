package com.gnostrenoff.cdb.dao.exception;

// TODO: Auto-generated Javadoc
/**
 * Exception thrown when properties files is not found while configuring connection.
 *
 * @author excilys
 */
public class PropertiesFileNotFoundException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new properties file not found exception.
   *
   * @param message the message
   */
  public PropertiesFileNotFoundException(String message) {
    super(message);
  }

}
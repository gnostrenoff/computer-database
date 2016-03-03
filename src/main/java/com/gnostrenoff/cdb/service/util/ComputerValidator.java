package com.gnostrenoff.cdb.service.util;

import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.exception.ComputerValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * The Class ComputerValidator.
 */
public class ComputerValidator {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class);

  /**
   * Validate.
   *
   * @param computer the computer
   */
  public static void validate(Computer computer) {

    String name = computer.getName();
    LocalDate introduced = computer.getIntroduced();
    LocalDate discontinued = computer.getDiscontinued();

    if (name == null || name.equals("")) {
      LOGGER.error("no name found in computer");
      throw new ComputerValidatorException("no name found in computer");
    }
    if (introduced != null && discontinued != null) {
      if (!computer.getDiscontinued().isAfter(computer.getIntroduced())) {
        LOGGER.error("invalid dates order");
        throw new ComputerValidatorException("invalid dates order");
      }
    }
    if (introduced == null && discontinued != null) {
      LOGGER.error("no introduced date");
      throw new ComputerValidatorException("no introduced date");
    }
  }

}

package com.gnostrenoff.cdb.service.util;

import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.exception.ServiceValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * The Class ComputerValidator.
 */
@Component
public class ComputerValidator {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class);

  /**
   * Validate the computer. Throws an exception if the computer is not valid .
   *
   * @param computer
   *          the computer
   */
  public void validate(Computer computer) {

    String name = computer.getName();
    LocalDate introduced = computer.getIntroduced();
    LocalDate discontinued = computer.getDiscontinued();
    Company company = computer.getCompany();

    if (company != null) {
      if (company.getId() < 0) {
        LOGGER.error("invalid company");
        throw new ServiceValidatorException("invalid company");
      }
    }
    if (name == null || name.equals("")) {
      LOGGER.error("no name found in computer");
      throw new ServiceValidatorException("no name found in computer");
    }
    if (introduced != null && discontinued != null) {
      if (!computer.getDiscontinued().isAfter(computer.getIntroduced())) {
        LOGGER.error("invalid dates order");
        throw new ServiceValidatorException("invalid dates order");
      }
    }
    if (introduced == null && discontinued != null) {
      LOGGER.error("no introduced date");
      throw new ServiceValidatorException("no introduced date");
    }
  }

}

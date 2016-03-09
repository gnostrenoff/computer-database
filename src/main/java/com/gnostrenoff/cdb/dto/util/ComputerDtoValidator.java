package com.gnostrenoff.cdb.dto.util;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.dto.exception.DtoInvalidComputerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The Class ComputerDtoValidator.
 */
public class ComputerDtoValidator {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDtoValidator.class);

  /**
   * Validate.
   *
   * @param computer
   *          the computer
   */
  public static void validate(ComputerDto computer) {

    boolean hasIntroduced = false;
    boolean hasDiscontinued = false;

    String name = computer.getName();
    if (name == null || name.isEmpty()) {
      throw new DtoInvalidComputerException("computer has no name");
    }

    String introduced = computer.getIntroduced();
    if (introduced != null && !introduced.isEmpty()) {
      computer.setIntroduced(introduced.replace('/', '-'));
      checkDates(computer.getIntroduced());
      hasIntroduced = true;
    }
    String discontinued = computer.getDiscontinued();
    if (discontinued != null && !discontinued.isEmpty()) {
      computer.setDiscontinued(discontinued.replace('/', '-'));
      checkDates(computer.getDiscontinued());
      hasDiscontinued = true;
    }

    if (!hasIntroduced && hasDiscontinued) {
      LOGGER.error("no introduced date");
      throw new DtoInvalidComputerException("no introduced date");
    }

  }

  /**
   * Check dates.
   *
   * @param date
   *          the date
   */
  public static void checkDates(String date) {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(date.trim());
    } catch (ParseException pe) {
      LOGGER.error("date invalid");
      throw new DtoInvalidComputerException("date invalid");
    }

  }

}

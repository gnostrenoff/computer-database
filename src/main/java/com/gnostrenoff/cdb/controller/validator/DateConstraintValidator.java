package com.gnostrenoff.cdb.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraintValidator implements ConstraintValidator<ComputerDate, String> {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(DateConstraintValidator.class);

  @Override
  public void initialize(ComputerDate arg0) {
  }

  @Override
  public boolean isValid(String dateField, ConstraintValidatorContext arg1) {
    return checkDates(dateField);
  }

  /**
   * Check dates.
   *
   * @param date
   *          the date
   */
  private boolean checkDates(String date) {

    if (date == null || date.isEmpty()) {
      return true;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(date.trim());
    } catch (ParseException pe) {
      LOGGER.error("date invalid");
      return false;
    }
    return true;
  }

}

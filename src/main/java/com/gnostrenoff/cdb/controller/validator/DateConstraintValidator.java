package com.gnostrenoff.cdb.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraintValidator implements ConstraintValidator<ComputerDate, String> {

  @Override
  public void initialize(ComputerDate arg0) {
  }

  @Override
  public boolean isValid(String dateField, ConstraintValidatorContext arg1) {
    if (dateField == null || dateField.isEmpty()) {
      return true;
    } else {
      return DateFormat.checkSyntax(dateField);
    }
  }

}

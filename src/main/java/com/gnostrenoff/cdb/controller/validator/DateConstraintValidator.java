package com.gnostrenoff.cdb.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class DateConstraintValidator implements ConstraintValidator<ComputerDate, String> {

  @Autowired
  private DateChecker dateChecker;

  @Override
  public void initialize(ComputerDate arg0) {
  }

  @Override
  public boolean isValid(String dateField, ConstraintValidatorContext arg1) {
    if (dateField == null || dateField.isEmpty()) {
      return true;
    } else {
      return dateChecker.checkSyntax(dateField);
    }
  }
}

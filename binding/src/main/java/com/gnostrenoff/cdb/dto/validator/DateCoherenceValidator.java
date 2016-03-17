package com.gnostrenoff.cdb.dto.validator;

import com.gnostrenoff.cdb.dto.ComputerDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class DateCoherenceValidator implements ConstraintValidator<DateCoherence, ComputerDto> {

  @Autowired
  private DateChecker dateChecker;

  @Override
  public void initialize(DateCoherence arg0) {
  }

  @Override
  public boolean isValid(ComputerDto computerDto, ConstraintValidatorContext ctx) {

    String introduced = computerDto.getIntroduced();
    String discontinued = computerDto.getDiscontinued();

    return dateChecker.checkCoherence(introduced, discontinued);
  }
}
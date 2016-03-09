package com.gnostrenoff.cdb.controller.validator;

import com.gnostrenoff.cdb.dto.ComputerDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateCoherenceValidator implements ConstraintValidator<DateCoherence, ComputerDto> {

  public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  public void initialize(DateCoherence arg0) {
  }

  @Override
  public boolean isValid(ComputerDto computerDto, ConstraintValidatorContext ctx) {

    String introduced = computerDto.getIntroduced();
    String discontinued = computerDto.getDiscontinued();

    if (discontinued != null && !discontinued.isEmpty()) {
      // Make sure introduced date is also set
      if (introduced == null || introduced.isEmpty()) {
        return false;
      } else {
        // check if dates order is correct
        return LocalDate.parse(introduced, FORMATTER)
            .isBefore(LocalDate.parse(discontinued, FORMATTER));
      }
    }
    return true;
  }
}
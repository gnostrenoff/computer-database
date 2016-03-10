package com.gnostrenoff.cdb.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Class DateFormat.
 */
@Component
public class DateChecker {

  /** The message source. */
  @Autowired
  private MessageSource messageSource;

  /**
   * Check syntax of given string date.
   *
   * @param date
   *          the date
   * @return true, if successful
   */
  public boolean checkSyntax(String date) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(
        messageSource.getMessage("util.dateFormat", null, LocaleContextHolder.getLocale()));

    dateFormat.setLenient(false);
    try {
      dateFormat.parse(date.trim());
    } catch (ParseException pe) {
      return false;
    }
    return true;
  }

  /**
   * Check coherence of two dates as Strings.
   *
   * @param introduced
   *          the introduced
   * @param discontinued
   *          the discontinued
   * @return true, if successful
   */
  public boolean checkCoherence(String introduced, String discontinued) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
        messageSource.getMessage("util.dateFormat", null, LocaleContextHolder.getLocale()));

    if (discontinued != null && !discontinued.isEmpty()) {
      // Make sure introduced date is also set
      if (introduced == null || introduced.isEmpty()) {
        return false;
      } else {
        // check if dates order is correct (check only if syntax is correct
        if (!checkSyntax(discontinued) || !checkSyntax(introduced)) {
          return true;
        }
        return LocalDate.parse(introduced, formatter)
            .isBefore(LocalDate.parse(discontinued, formatter));
      }
    }
    return true;
  }

  /**
   * Check coherence of two dates as LocalDates.
   *
   * @param introduced
   *          the introduced
   * @param discontinued
   *          the discontinued
   * @return true, if successful
   */
  public static boolean checkCoherence(LocalDate introduced, LocalDate discontinued) {
    if (discontinued != null) {
      // Make sure introduced date is also set
      if (introduced == null) {
        return false;
      } else {
        // check if dates order is correct (check only if syntax is correct
        return introduced.isBefore(discontinued);
      }
    }
    return true;
  }
}

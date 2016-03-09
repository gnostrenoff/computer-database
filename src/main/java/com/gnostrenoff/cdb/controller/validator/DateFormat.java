package com.gnostrenoff.cdb.controller.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormat {

  /**
   * Check syntax of given string date.
   *
   * @param date the date
   * @return true, if successful
   */
  public static boolean checkSyntax(String date) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(date.trim());
    } catch (ParseException pe) {
      return false;
    }
    return true;
  }

}

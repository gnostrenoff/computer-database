package com.gnostrenoff.cdbtests.junit.dto;

import static org.junit.Assert.fail;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.dto.exception.DtoInvalidComputerException;
import com.gnostrenoff.cdb.dto.util.ComputerDtoValidator;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class ValidatorTest.
 */
public class ValidatorDtoTest {

  /** The good dto1. */
  private static ComputerDto goodDto1;

  /** The good dto2. */
  private static ComputerDto goodDto2;

  /** The good dto3. */
  private static ComputerDto goodDto3;

  /** The bad dto1. */
  private static ComputerDto badDto1;

  /** The bad dto2. */
  private static ComputerDto badDto2;

  /** The bad dto3. */
  private static ComputerDto badDto3;

  /** The bad dto4. */
  private static ComputerDto badDto4;

  /**
   * Initialiation, executed once.
   */
  @BeforeClass
  public static void initOnce() {
    goodDto1 = new ComputerDto();
    goodDto1.setName("ok");
    goodDto2 = new ComputerDto("ok", "2015-03-12", null, null, 0);
    goodDto3 = new ComputerDto("ok", "2015/03/12", null, null, 0);

    badDto1 = new ComputerDto();
    badDto2 = new ComputerDto();
    badDto2.setName("");
    badDto3 = new ComputerDto("ok", null, "2015-03-12", null, 0);
    badDto4 = new ComputerDto("ok", "2015+03*12", null, null, 0);
  }

  /**
   * Validate good1.
   */
  @Test
  public void validateGood1() {
    try {
      ComputerDtoValidator.validate(goodDto1);
    } catch (DtoInvalidComputerException e) {
      fail();
    }
  }

  /**
   * Validate good2.
   */
  @Test
  public void validateGood2() {
    try {
      ComputerDtoValidator.validate(goodDto2);
    } catch (DtoInvalidComputerException e) {
      fail();
    }
  }

  /**
   * Validate good3.
   */
  @Test
  public void validateGood3() {
    try {
      ComputerDtoValidator.validate(goodDto3);
    } catch (DtoInvalidComputerException e) {
      fail();
    }
  }

  /**
   * Validate bad1.
   */
  @Test(expected = DtoInvalidComputerException.class)
  public void validateBad1() {
    ComputerDtoValidator.validate(badDto1);
  }

  /**
   * Validate bad2.
   */
  @Test(expected = DtoInvalidComputerException.class)
  public void validateBad2() {
    ComputerDtoValidator.validate(badDto2);
  }

  /**
   * Validate bad3.
   */
  @Test(expected = DtoInvalidComputerException.class)
  public void validateBad3() {
    ComputerDtoValidator.validate(badDto3);
  }

  /**
   * Validate bad4.
   */
  @Test(expected = DtoInvalidComputerException.class)
  public void validateBad4() {
    ComputerDtoValidator.validate(badDto4);
  }

}

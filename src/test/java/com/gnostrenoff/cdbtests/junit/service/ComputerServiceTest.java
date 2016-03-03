package com.gnostrenoff.cdbtests.junit.service;

import static org.junit.Assert.fail;

import com.gnostrenoff.cdb.dao.exception.DaoException;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.ComputerService;
import com.gnostrenoff.cdb.service.exception.ComputerValidatorException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

/**
 * The Class ComputerServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-context-test.xml" })
public class ComputerServiceTest {

  /** The computers. */
  private static Computer goodComputer, badComputer, badComputer2, badComputer3;

  /** The computer service. */
  @Autowired
  private ComputerService computerService;

  /** The computer dao mock. */
  @Autowired
  private ComputerDaoImpl computerDaoMock;

  /**
   * Initialiation, executed once.
   */
  @BeforeClass
  public static void initOnce() {
    goodComputer = new Computer("goodOne");
    goodComputer.setId(8);

    badComputer = new Computer("");

    badComputer2 = new Computer(null);

    badComputer3 = new Computer("ok");
    badComputer3.setIntroduced(LocalDate.of(2015, 02, 15));
    badComputer3.setDiscontinued(LocalDate.of(2015, 02, 14));

  }

  /**
   * Initialiation for each test.
   */
  @Before
  public void initTests() {

    // init dao mock
    Mockito.doNothing().when(computerDaoMock).create(goodComputer);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).create(badComputer);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).create(badComputer2);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).create(badComputer3);

    Mockito.doNothing().when(computerDaoMock).update(goodComputer);

    Mockito.doThrow(new DaoException("")).when(computerDaoMock).update(badComputer);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).update(badComputer2);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).update(badComputer3);

    Mockito.doNothing().when(computerDaoMock).delete(1);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).delete(0);
  }

  /**
   * Creates the bad.
   */
  @Test(expected = ComputerValidatorException.class)
  public void createBad() {
    computerService.create(badComputer);
  }

  /**
   * Creates the bad2.
   */
  @Test(expected = ComputerValidatorException.class)
  public void createBad2() {
    computerService.create(badComputer2);
  }

  /**
   * Creates the bad3.
   */
  @Test(expected = ComputerValidatorException.class)
  public void createBad3() {
    computerService.create(badComputer3);
  }

  /**
   * Creates the good.
   */
  @Test
  public void createGood() {
    try {
      computerService.create(goodComputer);
    } catch (ComputerValidatorException e) {
      fail();
    }
  }

  /**
   * Update bad.
   */
  @Test(expected = ComputerValidatorException.class)
  public void updateBad() {
    computerService.update(badComputer);
  }

  /**
   * Update bad2.
   */
  @Test(expected = ComputerValidatorException.class)
  public void updateBad2() {
    computerService.update(badComputer2);
  }

  /**
   * Update bad3.
   */
  @Test(expected = ComputerValidatorException.class)
  public void updateBad3() {
    computerService.update(badComputer3);
  }

  /**
   * Update good.
   */
  @Test
  public void updateGood() {
    try {
      computerService.update(goodComputer);
    } catch (ComputerValidatorException e) {
      fail();
    }

  }

  /**
   * Delete bad.
   */
  @Test(expected = ComputerValidatorException.class)
  public void deleteBad() {
    computerService.delete(0);
  }

  /**
   * Delete good.
   */
  @Test
  public void deleteGood() {
    try {
      computerService.delete(1);
    } catch (ComputerValidatorException e) {
      fail();
    }
  }
}

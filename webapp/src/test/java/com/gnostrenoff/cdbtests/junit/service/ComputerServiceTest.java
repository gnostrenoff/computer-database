package com.gnostrenoff.cdbtests.junit.service;

import static org.junit.Assert.fail;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.exception.DaoException;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.ComputerService;
import com.gnostrenoff.cdb.service.exception.ServiceValidatorException;

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
  private static Computer goodComputer;
  private static Computer badComputer;
  private static Computer badComputer2;
  private static Computer badComputer3;
  private static Computer badComputer4;
  private static Computer badComputer5;

  /** The computer service. */
  @Autowired
  private ComputerService computerService;

  /** The computer dao mock. */
  @Autowired
  private ComputerDao computerDaoMock;

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
    
    badComputer4 = new Computer("ok");
    badComputer4.setDiscontinued(LocalDate.of(2015, 02, 14));
    
    badComputer5 = new Computer("ok");
    badComputer5.setCompany(new Company(-1, "badOne"));

  }

  /**
   * Initialiation for each test.
   */
  @Before
  public void initTests() {

    // init dao mock
    Mockito.doNothing().when(computerDaoMock).save(goodComputer);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).save(badComputer);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).save(badComputer2);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).save(badComputer3);

    Mockito.doNothing().when(computerDaoMock).save(goodComputer);

    Mockito.doThrow(new DaoException("")).when(computerDaoMock).save(badComputer);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).save(badComputer2);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).save(badComputer3);

    Mockito.doNothing().when(computerDaoMock).delete(1L);
    Mockito.doThrow(new DaoException("")).when(computerDaoMock).delete(0L);
  }

  /**
   * Creates the bad.
   */
  @Test(expected = ServiceValidatorException.class)
  public void createBad() {
    computerService.create(badComputer);
  }

  /**
   * Creates the bad2.
   */
  @Test(expected = ServiceValidatorException.class)
  public void createBad2() {
    computerService.create(badComputer2);
  }

  /**
   * Creates the bad3.
   */
  @Test(expected = ServiceValidatorException.class)
  public void createBad3() {
    computerService.create(badComputer3);
  }
  
  /**
   * Creates the bad4.
   */
  @Test(expected = ServiceValidatorException.class)
  public void createBad4() {
    computerService.create(badComputer4);
  }
  
  /**
   * Creates the bad5.
   */
  @Test(expected = ServiceValidatorException.class)
  public void createBad5() {
    computerService.create(badComputer5);
  }

  /**
   * Creates the good.
   */
  @Test
  public void createGood() {
    try {
      computerService.create(goodComputer);
    } catch (ServiceValidatorException e) {
      fail();
    }
  }

  /**
   * Updates bad.
   */
  @Test(expected = ServiceValidatorException.class)
  public void updateBad() {
    computerService.update(badComputer);
  }

  /**
   * Updates bad2.
   */
  @Test(expected = ServiceValidatorException.class)
  public void updateBad2() {
    computerService.update(badComputer2);
  }

  /**
   * Updates bad3.
   */
  @Test(expected = ServiceValidatorException.class)
  public void updateBad3() {
    computerService.update(badComputer3);
  }
  
  /**
   * Updates the bad4.
   */
  @Test(expected = ServiceValidatorException.class)
  public void updateBad4() {
    computerService.create(badComputer4);
  }
  
  /**
   * Updates the bad5.
   */
  @Test(expected = ServiceValidatorException.class)
  public void updateBad5() {
    computerService.create(badComputer5);
  }

  /**
   * Updates good.
   */
  @Test
  public void updateGood() {
    try {
      computerService.update(goodComputer);
    } catch (ServiceValidatorException e) {
      fail();
    }

  }

  /**
   * Deletes bad.
   */
  @Test(expected = ServiceValidatorException.class)
  public void deleteBad() {
    computerService.delete(0);
  }
  
  /**
   * Deletes bad2.
   */
  @Test(expected = ServiceValidatorException.class)
  public void deleteBad2() {
    computerService.delete(-1);
  }

  /**
   * Deletes good.
   */
  @Test
  public void deleteGood() {
    try {
      computerService.delete(1);
    } catch (ServiceValidatorException e) {
      fail();
    }
  }
}
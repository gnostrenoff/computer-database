package com.gnostrenoff.cdbtests.junit.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.service.ComputerService;
import com.gnostrenoff.cdb.service.exception.ComputerValidatorException;
import com.gnostrenoff.cdb.service.impl.ComputerServiceImpl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerServiceTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ComputerDaoImpl.class)
@PowerMockIgnore({ "javax.management.*" })
public class ComputerServiceTest {

  /** The dao. */
  private static ComputerDao dao;
  
  /** The good computer. */
  private static Computer goodComputer;
  
  /** The bad computer3. */
  private static Computer badComputer, badComputer2, badComputer3;
  
  /** The computer service. */
  private static ComputerService computerService;

  /**
   * Inits the.
   */
  @BeforeClass
  public static void init() {
    dao = Mockito.mock(ComputerDaoImpl.class);
    Mockito.when(dao.getList(null)).thenReturn(new ArrayList<Computer>());
    Mockito.when(dao.get((long) 2)).thenReturn(new Computer());

    goodComputer = Mockito.mock(Computer.class);
    Mockito.when(goodComputer.getName()).thenReturn("goodOne");

    badComputer = Mockito.mock(Computer.class);
    Mockito.when(badComputer.getName()).thenReturn("");

    badComputer2 = Mockito.mock(Computer.class);
    Mockito.when(badComputer2.getName()).thenReturn(null);

    badComputer3 = Mockito.mock(Computer.class);
    Mockito.when(badComputer3.getName()).thenReturn("ok");
    Mockito.when(badComputer3.getIntroduced()).thenReturn(LocalDate.of(2015, 02, 15));
    Mockito.when(badComputer3.getDiscontinued()).thenReturn(LocalDate.of(2015, 02, 14));

    PowerMockito.mockStatic(ComputerDaoImpl.class);
    BDDMockito.given(ComputerDaoImpl.getInstance()).willReturn((ComputerDaoImpl) dao);

  }

  /**
   * Each time init.
   */
  @Before
  public void eachTimeInit() {
    computerService = ComputerServiceImpl.getInstance();
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

  /**
   * Gets the one computer.
   *
   * @return the one computer
   */
  @Test
  public void getOneComputer() {
    ComputerService computerService = ComputerServiceImpl.getInstance();
    assertTrue(computerService.get((long) 2) instanceof Computer);
  }

  /**
   * Gets the all computers.
   *
   * @return the all computers
   */
  @Test
  public void getAllComputers() {
    ComputerService computerService = ComputerServiceImpl.getInstance();
    List<Computer> computerList;
    QueryParams queryParams = new QueryParams(1, 10);
    queryParams.setOffset(0);
    computerList = computerService.getList(queryParams);
    assertTrue(computerList instanceof List<?>);
  }
}
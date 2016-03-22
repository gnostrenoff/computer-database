package com.gnostrenoff.cdbtests.junit.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.ComputerService;
import com.gnostrenoff.cdb.service.exception.ServiceValidatorException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

/**
 * The Class ComputerServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/service-context-test.xml" })
public class ComputerServiceTest {

  /** The company dao mock. */
  @Autowired
  private CompanyDao companyDao;

  /** The company dao mock. */
  @Autowired
  private ComputerDao computerDao;
  
  /** The computer service. */
  @Autowired
  private ComputerService computerService;
  
  private static Company company1;
  private static Company company2;

  /**
   * Inits the.
   */
  @Before
  public void init() {

    //first fill up test database
    
    if(computerDao.count() != 0) {
      computerDao.deleteAll();
    }
    if(companyDao.count() != 0) {
      companyDao.deleteAll();
    }
  
    company1 = new Company(1, "company 1");
    company2 = new Company(2, "company 2");
    companyDao.save(company1);  
    companyDao.save(company2);
    
    Computer computer1 = new Computer(1, "computer 1", LocalDate.of(2014, 05, 21), LocalDate.of(2015, 05, 21), company1);
    Computer computer2 = new Computer(2, "computer 2", LocalDate.of(2014, 05, 21), LocalDate.of(2015, 05, 21), company2);
    Computer computer3 = new Computer(3, "computer 3", LocalDate.of(2014, 05, 21), LocalDate.of(2015, 05, 21), company2);
    computerDao.save(computer1);
    computerDao.save(computer2);
    computerDao.save(computer3);

  }
  
  /**
   * Test creates a valid computer
   *
   * @return the all computers
   */
  @Test
  public void createGood() {
    Computer computerGood = new Computer(5, "computer good", LocalDate.of(2014, 05, 21), LocalDate.of(2015, 05, 21), company1);
    
    try{
      computerService.create(computerGood);
    } catch (ServiceValidatorException e) {
      fail();
    }   
    assertNotNull(computerDao.findOne(1L));
  }
  
  /**
   * Test creates a non valid computer
   *
   * @return the all computers
   */
  @Test
  public void createBad1() {
    Computer computerBad = new Computer(5, null, LocalDate.of(2014, 05, 21), LocalDate.of(2015, 05, 21), company1);
    
    try{
      computerService.create(computerBad);
      fail();
    } catch (ServiceValidatorException e) {
    }   
    
    Computer computer = computerDao.findOne(5L);
    
    assertNull(computer);
  }
  
  /**
   * Test creates a non valid computer
   *
   * @return the all computers
   */
  @Test
  public void createBad2() {
    Computer computerBad = new Computer(5, "", LocalDate.of(2014, 05, 21), LocalDate.of(2015, 05, 21), company1);
    
    try{
      computerService.create(computerBad);
      fail();
    } catch (ServiceValidatorException e) {
    }   
    
    assertNull(computerDao.findOne(5L));
  }
  
  /**
   * Test creates a non valid computer
   *
   * @return the all computers
   */
  @Test
  public void createBad3() {
    Computer computerBad = new Computer(5, "valid name", LocalDate.of(2014, 05, 21), LocalDate.of(2014, 05, 20), company1);
    
    try{
      computerService.create(computerBad);
      fail();
    } catch (ServiceValidatorException e) {
    }   
    
    assertNull(computerDao.findOne(5L));
  }
  
  /**
   * Test creates a non valid computer
   *
   * @return the all computers
   */
  @Test
  public void createBad4() {
    Computer computerBad = new Computer(5, "valid name", null, LocalDate.of(2014, 05, 20), company1);
    
    try{
      computerService.create(computerBad);
      fail();
    } catch (ServiceValidatorException e) {
    }   
    
    assertNull(computerDao.findOne(5L));
  }
  
  /**
   * Test updates a valid computer
   *
   * @return the all computers
   */
  @Test
  public void updateGood() {
    Computer computerGood = new Computer(5, "computer good", LocalDate.of(2014, 05, 21), LocalDate.of(2015, 05, 21), company1);
    
    try{
      computerService.create(computerGood);
    } catch (ServiceValidatorException e) {
      fail();
    }   
    assertNotNull(computerDao.findOne(1L));
  }
  
  /**
   * Test updates a non valid computer
   *
   * @return the all computers
   */
  @Test
  public void updateBad1() {
    Computer computerBad = computerDao.findOne(1L);
    computerBad.setName(null);
    
    try{
      computerService.update(computerBad);
      fail();
    } catch (ServiceValidatorException e) {
    }   
    
    Computer computerNotUpdated = computerDao.findOne(1L);
    assertNotNull(computerNotUpdated);
    assertNotNull(computerNotUpdated.getName());
  }
  
  /**
   * Test updates a non valid computer
   *
   * @return the all computers
   */
  @Test
  public void updateBad2() {
    Computer computerBad = computerDao.findOne(1L);
    computerBad.setName("");
    
    try{
      computerService.create(computerBad);
      fail();
    } catch (ServiceValidatorException e) {
    }   
    
    Computer computerNotUpdated = computerDao.findOne(1L);
    assertNotNull(computerNotUpdated);
    assertTrue(!computerNotUpdated.getName().isEmpty());
  }
  
  /**
   * Test updates a non valid computer
   *
   * @return the all computers
   */
  @Test
  public void updateBad3() {
    Computer computerBad = computerDao.findOne(1L);
    computerBad.setDiscontinued(LocalDate.of(2014, 5, 20));
    
    try{
      computerService.create(computerBad);
      fail();
    } catch (ServiceValidatorException e) {
    }   
    
    Computer computerNotUpdated = computerDao.findOne(1L);
    assertNotNull(computerNotUpdated);
    assertTrue(!computerNotUpdated.getDiscontinued().equals(LocalDate.of(2014, 5, 20)));
  }
  
  /**
   * Test updates a non valid computer
   *
   * @return the all computers
   */
  @Test
  public void updateBad4() {
    Computer computerBad = computerDao.findOne(1L);
    computerBad.setIntroduced(null);
    
    try{
      computerService.create(computerBad);
      fail();
    } catch (ServiceValidatorException e) {
    }   
    
    Computer computerNotUpdated = computerDao.findOne(1L);
    assertNotNull(computerNotUpdated);
    assertNotNull(computerNotUpdated.getIntroduced());
  }
  
  /**
   * Test deletes a valid computer
   *
   * @return the all computers
   */
  @Test
  public void deleteGood() {
    computerService.delete(1);
    assertNull(computerDao.findOne(1L));
  }
  
  /**
   * Test deletes a non valid computer
   *
   * @return the all computers
   */
  @Test(expected = ServiceValidatorException.class)
  public void deleteBad() {
    computerService.delete(-1);
  }
  
  /**
   * Test get a valid list of computers
   *
   * @return the all computers
   */
  @Test
  @Ignore
  public void list1() {
    PageRequest pageRequest = new PageRequest(1, 3);
    Page<Computer> page = computerService.getList(pageRequest, null);    
    assertTrue(page.getContent().size() == 3);    
  }
  
  /**
   * Test get a valid list of computers
   *
   * @return the all computers
   */
  @Test
  @Ignore
  public void list2() {
    PageRequest pageRequest = new PageRequest(1, 10, Direction.ASC, "name");
    Page<Computer> page = computerService.getList(pageRequest, "2"); 
    assertTrue(page.getContent().size() == 2);
  }
  
}

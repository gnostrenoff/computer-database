package com.gnostrenoff.cdbtests.junit.service;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.service.ComputerService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The Class CompanyServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/service-context-test.xml" })
public class CompanyServiceTest {

  /** The service. */
  @Autowired
  private ComputerService computerService;

  /** The company dao mock. */
  @Autowired
  private CompanyDao companyDao;

  /** The company dao mock. */
  @Autowired
  private ComputerDao computerDao;
  
  private static Company company1;
  private static Company company2;

  
  /**
   * Tests transaction while deleting a company by id.
   *
   * @return the all computers
   */
  @Test
  public void deleteById() {
  }

}

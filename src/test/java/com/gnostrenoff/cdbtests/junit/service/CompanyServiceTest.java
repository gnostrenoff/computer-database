package com.gnostrenoff.cdbtests.junit.service;

import static org.junit.Assert.assertTrue;

import com.gnostrenoff.cdb.service.CompanyService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context-test.xml" })
public class CompanyServiceTest {

  /** The service. */
  @Autowired
  private CompanyService companyService;

  /**
   * Gets the all computers.
   *
   * @return the all computers
   */
  @Test
  public void getAllComputers() {
    assertTrue(companyService.getList() instanceof List<?>);
  }

}

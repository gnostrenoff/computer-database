package com.gnostrenoff.cdbtests.junit.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.dto.mapper.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Locale;

/**
 * The Class ComputerDtoTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/binding-context-test.xml" })
public class ComputerDtoTest {
  
  @Autowired
  ComputerDtoMapper computerDtoMapper;

  /** The computer mock. */
  Computer computerMock;
  
  /** The company mock. */
  Company companyMock;
  
  /** The dto mock. */
  ComputerDto dtoMock;
  
  /** The dto mock. */
  @Autowired
  MessageSource messageSource;
  
  private static Company company;
  private static Computer computer;
  private static ComputerDto computerDto;

  /**
   * Before tests.
   */
  @Before
  public void beforeTests() { 
    company = new Company(1L, "Apple");
    computer = new Computer("mac", LocalDate.of(2014, 03, 12), LocalDate.of(2015, 03, 12), company);
    computerDto = new ComputerDto("mac", "2014-03-12", "2015-03-12", "Apple", 1L);
    
    Locale.setDefault(Locale.ENGLISH);
  }
  
  /**
   * After tests.
   */
  @After
  public void afterTests() { 
    company = null;
    computer = null;
    computerDto = null;
  }

  /**
   * To dto test.
   */
  @Test
  public void toDtoTest() {

    ComputerDto dto = computerDtoMapper.toDto(computer);

    assertNotNull(dto);
    assertTrue(dto.getName() instanceof String);
    assertEquals(dto.getName(), "mac");
    assertTrue(dto.getIntroduced() instanceof String);
    assertEquals(dto.getIntroduced(), "2014-03-12");
    assertTrue(dto.getDiscontinued() instanceof String);
    assertEquals(dto.getDiscontinued(), "2015-03-12");
    assertTrue(dto.getCompanyName() instanceof String);
    assertEquals(dto.getCompanyName(), "Apple");
    assertEquals(dto.getCompanyId(), (long) 1);
  }

  /**
   * To computer test.
   */
  @Test
  public void toComputerTest() {

    Computer computer = computerDtoMapper.toComputer(computerDto);

    assertNotNull(computer);
    assertTrue(computer.getName() instanceof String);
    assertEquals(computer.getName(), "mac");
    assertTrue(computer.getIntroduced() instanceof LocalDate);
    assertEquals(computer.getIntroduced(), LocalDate.of(2014, 03, 12));
    assertTrue(computer.getDiscontinued() instanceof LocalDate);
    assertEquals(computer.getDiscontinued(), LocalDate.of(2015, 03, 12));
    assertTrue(computer.getCompany().getName() instanceof String);
    assertEquals(computer.getCompany().getName(), "Apple");
  }

}

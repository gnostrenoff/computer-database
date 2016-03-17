package com.gnostrenoff.cdbtests.junit.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.dto.mapper.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

/**
 * The Class ComputerDtoTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-context-test.xml" })
@Ignore
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
  MessageSource messageSourceMock;

  /**
   * Before tests.
   */
  @Before
  public void beforeTests() {
    MockitoAnnotations.initMocks(this);
    computerMock = Mockito.mock(Computer.class);
    companyMock = Mockito.mock(Company.class);
    dtoMock = Mockito.mock(ComputerDto.class);

    Mockito.when(companyMock.getName()).thenReturn("apple");
    Mockito.when(companyMock.getId()).thenReturn((long) 1);

    Mockito.when(computerMock.getName()).thenReturn("macbookpro3000");
    Mockito.when(computerMock.getIntroduced()).thenReturn(LocalDate.of(2014, 03, 12));
    Mockito.when(computerMock.getDiscontinued()).thenReturn(LocalDate.of(2015, 03, 12));
    Mockito.when(computerMock.getCompany()).thenReturn(companyMock);

    Mockito.when(dtoMock.getName()).thenReturn("macbookpro3000");
    Mockito.when(dtoMock.getIntroduced()).thenReturn("2014-03-12");
    Mockito.when(dtoMock.getDiscontinued()).thenReturn("2015-03-12");
    Mockito.when(dtoMock.getCompanyName()).thenReturn("apple");
    Mockito.when(dtoMock.getCompanyId()).thenReturn((long) 1);
    
    Mockito
        .when(
            messageSourceMock.getMessage("util.dateFormat", null, LocaleContextHolder.getLocale()))
        .thenReturn("yyyy-MM-dd");
  }

  /**
   * To dto test.
   */
  @Test
  public void toDtoTest() {

    ComputerDto dto = computerDtoMapper.toDto(computerMock);

    assertNotNull(dto);
    assertTrue(dto.getName() instanceof String);
    assertEquals(dto.getName(), "macbookpro3000");
    assertTrue(dto.getIntroduced() instanceof String);
    assertEquals(dto.getIntroduced(), "2014-03-12");
    assertTrue(dto.getDiscontinued() instanceof String);
    assertEquals(dto.getDiscontinued(), "2015-03-12");
    assertTrue(dto.getCompanyName() instanceof String);
    assertEquals(dto.getCompanyName(), "apple");
    assertEquals(dto.getCompanyId(), (long) 1);
  }

  /**
   * To computer test.
   */
  @Test
  public void toComputerTest() {

    Computer computer = computerDtoMapper.toComputer(dtoMock);

    assertNotNull(computer);
    assertTrue(computer.getName() instanceof String);
    assertEquals(computer.getName(), "macbookpro3000");
    assertTrue(computer.getIntroduced() instanceof LocalDate);
    assertEquals(computer.getIntroduced(), LocalDate.of(2014, 03, 12));
    assertTrue(computer.getDiscontinued() instanceof LocalDate);
    assertEquals(computer.getDiscontinued(), LocalDate.of(2015, 03, 12));
    assertTrue(computer.getCompany().getName() instanceof String);
    assertEquals(computer.getCompany().getName(), "apple");
  }

}

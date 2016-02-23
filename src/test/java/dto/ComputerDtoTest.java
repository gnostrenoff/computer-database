package dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.dto.mappers.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDtoTest.
 */
public class ComputerDtoTest {

  /** The computer mock. */
  Computer computerMock;
  
  /** The company mock. */
  Company companyMock;
  
  /** The dto mock. */
  ComputerDto dtoMock;

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
  }

  /**
   * To dto test.
   */
  @Test
  public void toDtoTest() {

    ComputerDto dto = ComputerDtoMapper.toDto(computerMock);

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

    Computer computer = ComputerDtoMapper.toComputer(dtoMock);

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

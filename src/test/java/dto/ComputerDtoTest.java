package dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.mappers.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

public class ComputerDtoTest {

	Computer computerMock;
	Company companyMock;
	ComputerDto dtoMock;

	@Before
	public void beforeTests() {
		MockitoAnnotations.initMocks(this);
		computerMock = Mockito.mock(Computer.class);
		companyMock = Mockito.mock(Company.class);
		dtoMock = Mockito.mock(ComputerDto.class);
		;

		Mockito.when(companyMock.getName()).thenReturn("apple");
		Mockito.when(companyMock.getId()).thenReturn((long) 1);

		Mockito.when(computerMock.getName()).thenReturn("macbookpro3000");
		Mockito.when(computerMock.getIntroduced()).thenReturn(LocalDateTime.of(2014, 03, 12, 12, 35, 00));
		Mockito.when(computerMock.getDiscontinued()).thenReturn(LocalDateTime.of(2015, 03, 12, 12, 35, 00));
		Mockito.when(computerMock.getCompany()).thenReturn(companyMock);

		Mockito.when(dtoMock.getName()).thenReturn("macbookpro3000");
		Mockito.when(dtoMock.getIntroduced()).thenReturn("2014-03-12 12:35");
		Mockito.when(dtoMock.getDiscontinued()).thenReturn("2015-03-12 12:35");
		Mockito.when(dtoMock.getCompany()).thenReturn("apple");
	}

	@Test
	public void toDtoTest() {

		ComputerDto dto = ComputerDtoMapper.toDto(computerMock);

		assertNotNull(dto);
		assertTrue(dto.getName() instanceof String);
		assertEquals(dto.getName(), "macbookpro3000");
		assertTrue(dto.getIntroduced() instanceof String);
		assertEquals(dto.getIntroduced(), "2014-03-12 12:35");
		assertTrue(dto.getDiscontinued() instanceof String);
		assertEquals(dto.getDiscontinued(), "2015-03-12 12:35");
		assertTrue(dto.getCompany() instanceof String);
		assertEquals(dto.getCompany(), "apple");
	}

	@Test
	public void toComputerTest() {

		Computer computer = ComputerDtoMapper.toComputer(dtoMock);

		assertNotNull(computer);
		assertTrue(computer.getName() instanceof String);
		assertEquals(computer.getName(), "macbookpro3000");
		assertTrue(computer.getIntroduced() instanceof LocalDateTime);
		assertEquals(computer.getIntroduced(), LocalDateTime.of(2014, 03, 12, 12, 35, 00));
		assertTrue(computer.getDiscontinued() instanceof LocalDateTime);
		assertEquals(computer.getDiscontinued(), LocalDateTime.of(2015, 03, 12, 12, 35, 00));
		assertTrue(computer.getCompany().getName() instanceof String);
		assertEquals(computer.getCompany().getName(), "apple");
	}

}
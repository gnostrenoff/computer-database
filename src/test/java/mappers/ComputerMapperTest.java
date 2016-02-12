package mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.gnostrenoff.cdb.mappers.ComputerMapper;
import com.gnostrenoff.cdb.model.Computer;

@Ignore
public class ComputerMapperTest {

	ResultSet rs;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		rs = Mockito.mock(ResultSet.class);
		try {
			Mockito.when(rs.getString("computer.name")).thenReturn("macbookpro3000");
			Mockito.when(rs.getString("company.name")).thenReturn("apple");
			Mockito.when(rs.getTimestamp("introduced")).thenReturn(new Timestamp(2015, 03, 24, 10, 30, 54, 00));
			Mockito.when(rs.getTimestamp("discontinued")).thenReturn(new Timestamp(2015, 05, 24, 10, 30, 54, 00));
			Mockito.when(rs.getLong("computer.id")).thenReturn((long) 10);
			Mockito.when(rs.getLong("company.id")).thenReturn((long) 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void clear() {
	}

	@Test
	public void mapTest() {
		Computer computer = ComputerMapper.map(rs);
		assertEquals("macbookpro3000", computer.getName());
		assertEquals("apple", computer.getCompany().getName());
		assertEquals((long) 10, computer.getId());
		assertEquals((long) 1, computer.getCompany().getId());

		LocalDateTime introduced = computer.getIntroduced();
		LocalDateTime discontinued = computer.getDiscontinued();

		assertTrue(introduced.getYear() == 2015);
		assertTrue(introduced.getMonthValue() == 03);
		assertTrue(introduced.getDayOfMonth() == 24);
		assertTrue(introduced.getHour() == 10);
		assertTrue(introduced.getMinute() == 30);
		assertTrue(introduced.getSecond() == 54);
		assertTrue(introduced.getNano() == 0);		
		
		assertTrue(discontinued.getYear() == 2015);
		assertTrue(discontinued.getMonthValue() == 05);
		assertTrue(discontinued.getDayOfMonth() == 24);
		assertTrue(discontinued.getHour() == 10);
		assertTrue(discontinued.getMinute() == 30);
		assertTrue(discontinued.getSecond() == 54);
		assertTrue(discontinued.getNano() == 0);

	}

}

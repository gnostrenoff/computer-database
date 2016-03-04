package com.gnostrenoff.cdbtests.junit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.gnostrenoff.cdb.dao.mapper.ComputerDaoMapper;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * The Class DaoMapperTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-context-test.xml" })
public class DaoMapperTest {

  /** The rs2. */
  private static ResultSet rs, rs2;

  /**
   * Inits the.
   */
  @BeforeClass
  public static void init() {

    rs = Mockito.mock(ResultSet.class);
    rs2 = Mockito.mock(ResultSet.class);

    // resulset test 1
    try {
      Mockito.when(rs.getString("computer.name")).thenReturn("nameOfComputer");
      Mockito.when(rs.getString("company.name")).thenReturn("companyNameOfComputer");
      Mockito.when(rs.getLong("computer.id")).thenReturn(1L);
      Mockito.when(rs.getLong("company.id")).thenReturn(1L);
      Mockito.when(rs.getTimestamp("introduced"))
          .thenReturn(new Timestamp(2014, 3, 21, 0, 0, 0, 0));
      Mockito.when(rs.getTimestamp("discontinued"))
          .thenReturn(new Timestamp(2015, 8, 14, 0, 0, 0, 0));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    // resulset test 2
    try {
      Mockito.when(rs2.getString("computer.name")).thenReturn("nameOfComputer");
      Mockito.when(rs2.getLong("computer.id")).thenReturn(1L);
      Mockito.when(rs2.getTimestamp("introduced"))
          .thenReturn(null);
      Mockito.when(rs2.getTimestamp("discontinued"))
          .thenReturn(null);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * Test1.
   */
  @Test
  public void test1() {

    Computer computer = ComputerDaoMapper.map(rs);

    assertEquals(computer.getName(), "nameOfComputer");
    assertEquals(computer.getId(), 1L);
    assertEquals(computer.getIntroduced(),
        (new Timestamp(2014, 3, 21, 0, 0, 0, 0)).toLocalDateTime().toLocalDate());
    assertEquals(computer.getDiscontinued(),
        (new Timestamp(2015, 8, 14, 0, 0, 0, 0)).toLocalDateTime().toLocalDate());

    Company company = computer.getCompany();
    assertNotNull(company);
    assertEquals(company.getName(), "companyNameOfComputer");
    assertEquals(company.getId(), 1L);
  }

  /**
   * Test2.
   */
  @Test
  public void test2() {

    Computer computer = ComputerDaoMapper.map(rs2);

    assertEquals(computer.getName(), "nameOfComputer");
    assertEquals(computer.getId(), 1L);
    assertNull(computer.getIntroduced());
    assertNull(computer.getDiscontinued());

    Company company = computer.getCompany();
    assertNotNull(company);
    assertNull(company.getName());
    assertEquals(company.getId(), 0);
  }
}

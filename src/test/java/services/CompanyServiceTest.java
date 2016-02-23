package services;

import static org.junit.Assert.assertTrue;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.impl.CompanyDaoImpl;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.services.CompanyService;
import com.gnostrenoff.cdb.services.impl.CompanyServiceImpl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyServiceTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CompanyDaoImpl.class)
@PowerMockIgnore({ "javax.management.*" })
public class CompanyServiceTest {

  /** The dao. */
  private static CompanyDao dao;

  /**
   * Inits the.
   */
  @BeforeClass
  public static void init() {
    dao = Mockito.mock(CompanyDaoImpl.class);
    Mockito.when(dao.getList()).thenReturn(new ArrayList<Company>());

    PowerMockito.mockStatic(CompanyDaoImpl.class);
    BDDMockito.given(CompanyDaoImpl.getInstance()).willReturn((CompanyDaoImpl) dao);

  }

  /**
   * Gets the all computers.
   *
   * @return the all computers
   */
  @Test
  public void getAllComputers() {
    CompanyService companyService = CompanyServiceImpl.getInstance();
    assertTrue(companyService.getList() instanceof List<?>);
  }

}

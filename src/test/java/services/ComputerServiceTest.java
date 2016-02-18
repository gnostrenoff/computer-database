package services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.services.ComputerService;
import com.gnostrenoff.cdb.services.impl.ComputerServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ComputerDaoImpl.class)
public class ComputerServiceTest {

	private static ComputerDao dao;

	@BeforeClass
	public static void init() {
		dao = Mockito.mock(ComputerDaoImpl.class);
		Mockito.when(dao.getList(null)).thenReturn(new ArrayList<Computer>());
		Mockito.when(dao.get((long) 2)).thenReturn(new Computer());

		PowerMockito.mockStatic(ComputerDaoImpl.class);
		BDDMockito.given(ComputerDaoImpl.getInstance()).willReturn((ComputerDaoImpl) dao);

	}

	@Test
	public void getOneComputer() {
		ComputerService computerService = ComputerServiceImpl.getInstance();
		assertTrue(computerService.get((long) 2) instanceof Computer);
	}

	@Test
	public void getAllComputers() {
		ComputerService computerService = ComputerServiceImpl.getInstance();
		List<Computer> computerList;
		QueryParams queryParams = new QueryParams(1, 10);
		queryParams.setOffset(0);
		computerList = computerService.getList(queryParams);
		assertTrue(computerList instanceof List<?>);
	}

}

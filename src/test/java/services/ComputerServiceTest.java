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
import com.gnostrenoff.cdb.services.ComputerService;
import com.gnostrenoff.cdb.services.impl.ComputerServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ComputerDaoImpl.class)
public class ComputerServiceTest {
	
	private static ComputerDao dao;
	
	@BeforeClass
	public static void init(){
		dao = Mockito.mock(ComputerDaoImpl.class);
		Mockito.when(dao.getComputers()).thenReturn(new ArrayList<Computer>());
		Mockito.when(dao.getComputer((long)2)).thenReturn(new Computer());
		
		PowerMockito.mockStatic(ComputerDaoImpl.class);
		BDDMockito.given(ComputerDaoImpl.getInstance()).willReturn((ComputerDaoImpl) dao);
		
	}
	
	@Test
	public void getOneComputer(){
		ComputerService computerService = ComputerServiceImpl.getInstance();
		assertTrue(computerService.getComputer((long)2) instanceof Computer);
	}
	
	@Test
	public void getAllComputers(){
		ComputerService computerService = ComputerServiceImpl.getInstance();
		assertTrue(computerService.getComputers() instanceof List<?>);
	}	

}

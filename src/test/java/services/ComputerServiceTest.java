package services;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.services.ComputerService;
import com.gnostrenoff.cdb.services.exceptions.ComputerValidatorException;
import com.gnostrenoff.cdb.services.impl.ComputerServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ComputerDaoImpl.class)
@PowerMockIgnore({ "javax.management.*" })
public class ComputerServiceTest {

	private static ComputerDao dao;
	private static Computer goodComputer;
	private static Computer badComputer, badComputer2, badComputer3;
	private static ComputerService computerService;

	@BeforeClass
	public static void init() {
		dao = Mockito.mock(ComputerDaoImpl.class);
		Mockito.when(dao.getList(null)).thenReturn(new ArrayList<Computer>());
		Mockito.when(dao.get((long) 2)).thenReturn(new Computer());
		
		goodComputer = Mockito.mock(Computer.class);
		Mockito.when(goodComputer.getName()).thenReturn("goodOne");
		
		badComputer = Mockito.mock(Computer.class);
		Mockito.when(badComputer.getName()).thenReturn("");
		
		badComputer2 = Mockito.mock(Computer.class);
		Mockito.when(badComputer2.getName()).thenReturn(null);
		
		badComputer3 = Mockito.mock(Computer.class);
		Mockito.when(badComputer3.getName()).thenReturn("ok");
		Mockito.when(badComputer3.getIntroduced()).thenReturn(LocalDate.of(2015, 02, 15));
		Mockito.when(badComputer3.getDiscontinued()).thenReturn(LocalDate.of(2015, 02, 14));

		PowerMockito.mockStatic(ComputerDaoImpl.class);
		BDDMockito.given(ComputerDaoImpl.getInstance()).willReturn((ComputerDaoImpl) dao);

	}
	
	@Before
	public void eachTimeInit(){
		computerService = ComputerServiceImpl.getInstance();
	}
	
	@Test (expected = ComputerValidatorException.class)
	public void createBad() {
		computerService.create(badComputer);
	}
	@Test (expected = ComputerValidatorException.class)
	public void createBad2() {
		computerService.create(badComputer2);
	}
	@Test (expected = ComputerValidatorException.class)
	public void createBad3() {
		computerService.create(badComputer3);
	}

	@Test
	public void createGood() {
		try{
			computerService.create(goodComputer);
		} catch(ComputerValidatorException e){
			fail();
		}
		
	}

	@Test (expected = ComputerValidatorException.class)
	public void updateBad() {
		computerService.update(badComputer);
	}
	@Test (expected = ComputerValidatorException.class)
	public void updateBad2() {
		computerService.update(badComputer2);
	}
	@Test (expected = ComputerValidatorException.class)
	public void updateBad3() {
		computerService.update(badComputer3);
	}

	@Test
	public void updateGood() {
		try{
			computerService.update(goodComputer);
		} catch(ComputerValidatorException e){
			fail();
		}
		
	}
	
	@Test (expected = ComputerValidatorException.class)
	public void deleteBad() {
		computerService.delete(0);
	}
	
	@Test
	public void deleteGood() {
		try{
			computerService.delete(1);
		} catch(ComputerValidatorException e){
			fail();
		}		
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

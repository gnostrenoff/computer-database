package daotests;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;

public class DaoComputerTest{
	
	private ComputerDao computerDao;
	
	@Before
	public void init(){
		computerDao = ComputerDaoImpl.getInstance();
	}
	
	@After
	public void clear(){
		computerDao = null;
	}
		
	@Test
	public void testInstanciation(){		 
		fail();
	}
	
	@Test
	public void create(){		 
		fail();
	}
	
	@Test
	public void get(){		 
		fail();
	}
	
	@Test
	public void update(){		 
		fail();
	}
	
	@Test
	public void delete(){		 
		fail();
	}

}

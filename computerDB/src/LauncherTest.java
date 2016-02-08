
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import dao.CompanyDao;
import dao.ComputerDao;
import dao.DaoFactory;
import model.Company;
import model.Computer;

public class LauncherTest {

	public static void main(String[] args) {
		
		DaoFactory daoFactory = DaoFactory.getInstance();
		ComputerDao computerDao = daoFactory.getComputerDao();
		CompanyDao companyDao = daoFactory.getCompanyDao();
		List<Computer> computerList;
		
		//create a new computer
		Computer computer = new Computer("new computer", new Timestamp((new Date()).getTime()) , null, 1);
		computerDao.createComputer(computer);
		
		//check if the new entity actually exists in database
		System.out.println(computerDao.getComputer(computer.getId()).toString());
		
		//change it, update it and display it again
		computer.setName("new computer updated");
		computerDao.updateComputer(computer);
		System.out.println(computerDao.getComputer(computer.getId()).toString());
		
		//delete it
		computerDao.deleteComputer(computer.getId());
		
		//display
		computerList = computerDao.getComputers();
		for(int i = 0; i < computerList.size(); i++){
			Computer comp = computerList.get(i);
			System.out.println(comp.toString());
		}		
		
	}

}

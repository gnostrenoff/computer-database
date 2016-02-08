import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.CompanyDao;
import dao.ComputerDao;
import dao.DaoFactory;
import model.Company;
import model.Computer;

public class Listener {

	public static void main(String[] args) {
		
		DaoFactory daoFactory = DaoFactory.getInstance();
		ComputerDao computerDao = daoFactory.getComputerDao();
		CompanyDao companyDao = daoFactory.getCompanyDao();
		
		Scanner scanIn = new Scanner(System.in);
		int input;
		boolean exit = false;
		
		while(!exit){
			
			System.out.println("\n\n");
			System.out.println("what do you want to do ?");
			System.out.println("\t 1 - list all companies");
			System.out.println("\t 2 - list all computers");
			System.out.println("\t 3 - create computer");
			System.out.println("\t 4 - read detail computer");
			System.out.println("\t 5 - update computer");
			System.out.println("\t 6 - delete computer");
			System.out.println("\t 7 - exit");
			input = scanIn.nextInt();
		    
		    switch(input){
			    case 1:
			    	List<Company> companiesList = companyDao.getCompanies();
					for(int i = 0; i < companiesList.size(); i++){
						Company comp = companiesList.get(i);
						System.out.println(comp.toString());
					}
			    	break;
			    case 2:
			    	List<Computer> computerList = computerDao.getComputers();
					for(int i = 0; i < computerList.size(); i++){
						Computer comp = computerList.get(i);
						System.out.println(comp.toString());
					}
			    	break;
			    case 3:
			    	Computer newComputer = new Computer();
			    	System.out.println("please enter a name :");
			    	scanIn.nextLine(); //empty previous line
					newComputer.setName(scanIn.nextLine());		
					System.out.println("please enter id of manufacturer :");
					newComputer.setCompanyId(scanIn.nextLong());
					newComputer.setIntroduced(new Timestamp((new Date()).getTime()));
					computerDao.createComputer(newComputer);
					System.out.println("computer " + newComputer.getId() + " has been succesfully added to database");
			    	break;
			    case 4:
			    	System.out.println("please enter a id :");
			    	System.out.println(computerDao.getComputer(scanIn.nextLong()).toString());	    	
			    	break;
			    case 5:
			    	System.out.println("please enter a id :");
			    	Computer computer = computerDao.getComputer(scanIn.nextLong());
			    	System.out.println("please enter a new name :");
			    	scanIn.nextLine(); //empty previous line
					computer.setName(scanIn.nextLine());		
					System.out.println("please enter id of manufacturer :");
					computer.setCompanyId(scanIn.nextLong());
					computerDao.updateComputer(computer);
					System.out.println("computer " + computer.getId() + " has been succesfully updated in database");
			    	break;
			    case 6:
			    	System.out.println("please enter a id :");
			    	long id = scanIn.nextLong();
			    	computerDao.deleteComputer(id);
			    	System.out.println("computer " + id + " has been succesfully deleted from database");
			    	break;
			    case 7:
			    	exit = true;
			    	break;
			    default:
		    	    System.out.println("command not reconized");
		    	    break;
		    }
		}
		scanIn.close();  
		System.out.println("exit ...");
		System.exit(1);
	}
}

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.CompanyDao;
import dao.ComputerDao;
import dao.DaoFactory;
import model.Company;
import model.Computer;

public class Listener {
		
		public static final DaoFactory daoFactory = DaoFactory.getInstance();
		public static final ComputerDao computerDao = daoFactory.getComputerDao();
		public static final CompanyDao companyDao = daoFactory.getCompanyDao();
		
		public static final Scanner scanIn = new Scanner(System.in);
		public static final SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		public static boolean exit = false;
		public static int input;
		
		public void listen(){
			while(!exit){
				
				System.out.println("\n\n");
				System.out.println("what do you want to do ?\n");
				System.out.println("\t * 1 - list all companies");
				System.out.println("\t * 2 - list all computers");
				System.out.println("\t * 3 - create computer");
				System.out.println("\t * 4 - read detail computer");
				System.out.println("\t * 5 - update computer");
				System.out.println("\t * 6 - delete computer");
				System.out.println("\t * 7 - exit");
				input = scanIn.nextInt();
			    
			    switch(input){
				    case 1:
				    	listCompanies();
				    	break;
				    case 2:
				    	listComputers();
				    	break;
				    case 3:
				    	create();
				    	break;
				    case 4:
				    	readDetails();	    	
				    	break;
				    case 5:
				    	update();
				    	break;
				    case 6:
				    	delete();
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
			System.exit(0);
		}
		
		private void listCompanies(){
			List<Company> companiesList = companyDao.getCompanies();
			for(int i = 0; i < companiesList.size(); i++){
				Company comp = companiesList.get(i);
				System.out.println(comp.toString());
			}
		}
		
		private void listComputers(){
			List<Computer> computerList = computerDao.getComputers();
			for(int i = 0; i < computerList.size(); i++){
				Computer comp = computerList.get(i);
				System.out.println(comp.toString());
			}
		}
		
		private void create(){
			Computer newComputer = new Computer();
	    	System.out.println("please enter a name :");
	    	scanIn.nextLine(); //empty previous line
			newComputer.setName(scanIn.nextLine());		
			System.out.println("please enter date when the computer was introduced :");
			newComputer.setIntroduced(waitForValidDate(newComputer));
			System.out.println("please enter date when the computer was discontinued :");
			newComputer.setDiscontinued(waitForValidDate(newComputer));
			System.out.println("please enter id of manufacturer :");
			newComputer.setCompanyId(scanIn.nextLong());
			//newComputer.setIntroduced(new Timestamp((new Date()).getTime()));
			computerDao.createComputer(newComputer);
			System.out.println("computer " + newComputer.getId() + " has been succesfully added to database");
		}
		
		private void readDetails(){
			System.out.println("please enter a id :");
	    	System.out.println(computerDao.getComputer(scanIn.nextLong()).toString());
		}
		
		private void update(){
			System.out.println("please enter a id :");
	    	Computer computer = computerDao.getComputer(scanIn.nextLong());
	    	System.out.println("please enter a new name :");
	    	scanIn.nextLine(); //empty previous line
			computer.setName(scanIn.nextLine());		
			System.out.println("please enter date when the computer was introduced :");
			computer.setIntroduced(waitForValidDate(computer));
			System.out.println("please enter date when the computer was discontinued :");
			computer.setDiscontinued(waitForValidDate(computer));
			System.out.println("please enter id of manufacturer :");
			computer.setCompanyId(scanIn.nextLong());
			computerDao.updateComputer(computer);
			System.out.println("computer " + computer.getId() + " has been succesfully updated in database");
		}
		
		private void delete(){
			System.out.println("please enter a id :");
	    	long id = scanIn.nextLong();
	    	computerDao.deleteComputer(id);
	    	System.out.println("computer " + id + " has been succesfully deleted from database");
		}
		
		@SuppressWarnings("deprecation")
		private Timestamp waitForValidDate(Computer computer){
			while(true){
				//scanIn.nextLine(); //empty previous line
				String sdate = scanIn.nextLine();
				if(sdate.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")){
					return new Timestamp((new Date(sdate)).getTime());
				}
				else {
				    System.out.println("Erreur format");
				}
			}
		}
		
		
}

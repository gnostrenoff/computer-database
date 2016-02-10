package com.gnostrenoff.cdb.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.gnostrenoff.cdb.dao.JDBCConnection;
import com.gnostrenoff.cdb.exceptions.DatesNotCorrectException;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.services.CompanyService;
import com.gnostrenoff.cdb.services.ComputerService;
import com.gnostrenoff.cdb.services.impl.CompanyServiceImpl;
import com.gnostrenoff.cdb.services.impl.ComputerServiceImpl;

public class Listener {
		
		public static final JDBCConnection jdbcConnection = JDBCConnection.getInstance();
		public static final ComputerService computerService = ComputerServiceImpl.getInstance();
		public static final CompanyService companyService = CompanyServiceImpl.getInstance();
		
		public static final Scanner scanIn = new Scanner(System.in);
		public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		public static boolean exit = false;
		public static int input;
		
		public void listen(){
			while(!exit){
				
				displayMenu();
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
		
		private void displayMenu(){
			System.out.println("\n\n");
			System.out.println("\t############################");
			System.out.println("\t# what do you want to do ?");
			System.out.println("\t#");
			System.out.println("\t#  * 1 - list all companies");
			System.out.println("\t#  * 2 - list all computers");
			System.out.println("\t#  * 3 - create computer");
			System.out.println("\t#  * 4 - read detail computer");
			System.out.println("\t#  * 5 - update computer");
			System.out.println("\t#  * 6 - delete computer");
			System.out.println("\t#  * 7 - exit");
			System.out.println("\t############################");
		}
		
		private void listCompanies(){
			List<Company> companiesList = companyService.getCompanies();
			for(int i = 0; i < companiesList.size(); i++){
				Company comp = companiesList.get(i);
				System.out.println(comp.toString());
			}
		}
		
		private void listComputers(){
			List<Computer> computerList = computerService.getComputers();
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
			
			System.out.println("please enter date when the computer was introduced (date format : yyyy-mm-dd hh:mm:ss) :");
			newComputer.setIntroduced(waitForValidDate(newComputer));
			System.out.println("please enter date when the computer was discontinued (date format : yyyy-mm-dd hh:mm:ss) :");
			newComputer.setDiscontinued(waitForValidDate(newComputer));
			System.out.println("please enter id of manufacturer :");
			
			Company company = new Company();
			company.setId(scanIn.nextLong());
			newComputer.setCompany(company);
			
			try{
				computerService.createComputer(newComputer);
				System.out.println("computer " + newComputer.getId() + " has been succesfully added to database");
			}catch(DatesNotCorrectException e){
				System.out.println("computer " + newComputer.getId() + " was NOT added to database because the computer cannot be discontinued before being introduced");
			}
		
		}
		
		private void readDetails(){
			System.out.println("please enter a id :");
	    	System.out.println(computerService.getComputer(scanIn.nextLong()).toString());
		}
		
		private void update(){
			System.out.println("please enter a id :");
	    	Computer computer = computerService.getComputer(scanIn.nextLong());
	    	System.out.println("please enter a new name :");
	    	scanIn.nextLine(); //empty previous line
			computer.setName(scanIn.nextLine());		
			
			System.out.println("please enter date when the computer was introduced (date format : yyyy-mm-dd hh:mm:ss) :");
			computer.setIntroduced(waitForValidDate(computer));
			System.out.println("please enter date when the computer was discontinued (date format : yyyy-mm-dd hh:mm:ss) :");
			computer.setDiscontinued(waitForValidDate(computer));
			System.out.println("please enter id of manufacturer :");

			Company company = new Company();
			company.setId(scanIn.nextLong());
			computer.setCompany(company);
			
			try{
				computerService.createComputer(computer);
				System.out.println("computer " + computer.getId() + " has been succesfully updated to database");
			}catch(DatesNotCorrectException e){
				System.out.println("computer " + computer.getId() + " was NOT updated in database because the computer cannot be discontinued before being introduced");
			}
		}
		
		private void delete(){
			System.out.println("please enter a id :");
	    	long id = scanIn.nextLong();
	    	computerService.deleteComputer(id);
	    	System.out.println("computer " + id + " has been succesfully deleted from database");
		}
		
		private LocalDateTime waitForValidDate(Computer computer){
	
			LocalDateTime dateTime = null;
	
			while(dateTime == null){
				String sdate = scanIn.nextLine();
				try{
					dateTime = LocalDateTime.parse(sdate, formatter);
				} catch(DateTimeParseException e){
					dateTime = null;
					System.out.println("date not valid, try again");
				}					
			}
			return dateTime;
		}
}

package com.gnostrenoff.cdb.cli;

import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.CompanyService;
import com.gnostrenoff.cdb.service.ComputerService;
import com.gnostrenoff.cdb.service.exception.ServiceValidatorException;
import com.gnostrenoff.cdb.service.impl.CompanyServiceImpl;
import com.gnostrenoff.cdb.service.impl.ComputerServiceImpl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * The Class Listener.
 */
public class Listener {

  /** The application context. */
  public static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
      "classpath:/console-context.xml");

  /** The Constant scanIn. */
  public static final Scanner scanIn = new Scanner(System.in);

  /** The Constant formatter. */
  public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  /** The exit. */
  public static boolean exit = false;

  /** The input. */
  public static int input;

  private CompanyService companyService;
  private ComputerService computerService;
  
  
//  Format json data to respect
//  {
//    "id": 74,
//    "name": "Amiga 2000",
//    "introduced": "1986-01-01",
//    "discontinued": "1990-01-01",
//    "companyName": "Commodore International",
//    "companyId": 6
//  }
  
 
  /**
   * Listen.
   */
  public void listen() {

    companyService = applicationContext.getBean("companyService", CompanyServiceImpl.class);
    computerService = applicationContext.getBean("computerService", ComputerServiceImpl.class);

    while (!exit) {

      displayMenu();
      input = scanIn.nextInt();

      switch (input) {
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
        deleteSingleComputer();
        break;
      case 7:
        deleteByCompanyId();
        break;
      case 8:
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

  /**
   * Display menu.
   */
  private void displayMenu() {
    System.out.println("\n\n");
    System.out.println("\t===========================================");
    System.out.println("\t|   SELECT YOUR OPTION                    |");
    System.out.println("\t===========================================");
    System.out.println("\t| Options:                                |");
    System.out.println("\t|        1. list all companies            |");
    System.out.println("\t|        2. list all computers            |");
    System.out.println("\t|        3. create computer               |");
    System.out.println("\t|        4. read detail computer          |");
    System.out.println("\t|        5. update computer               |");
    System.out.println("\t|        6. delete computer               |");
    System.out.println("\t|        7. delete computer by company id |");
    System.out.println("\t|        8. exit                          |");
    System.out.println("\t===========================================");
  }

  /**
   * List companies.
   */
  private void listCompanies() {
    List<Company> companiesList = companyService.getList();
    for (int i = 0; i < companiesList.size(); i++) {
      Company comp = companiesList.get(i);
      System.out.println(comp.toString());
    }
  }

  /**
   * List computers.
   */
  private void listComputers() {
    long initialNbComputers = computerService.count();
    long nbComputers = initialNbComputers;
    
  }

  /**
   * Creates the.
   */
  private void create() {
    Computer newComputer = new Computer();
    System.out.println("please enter a name :");
    scanIn.nextLine(); // empty previous line
    newComputer.setName(scanIn.nextLine());

    System.out
        .println("please enter date when the computer was introduced (date format : yyyy/MM/dd) :");
    newComputer.setIntroduced(waitForValidDate(newComputer));
    System.out.println(
        "please enter date when the computer was discontinued (date format : yyyy/MM/dd) :");
    newComputer.setDiscontinued(waitForValidDate(newComputer));
    System.out.println("please enter id of manufacturer :");

    Company company = new Company();
    company.setId(scanIn.nextLong());
    newComputer.setCompany(company);

    try {
      computerService.create(newComputer);
      System.out
          .println("computer " + newComputer.getId() + " has been succesfully added to database");
    } catch (ServiceValidatorException e) {
      System.out.println("computer " + newComputer.getId()
          + " was NOT added to database because the dates are not correct");
    }

  }

  /**
   * Read details.
   */
  private void readDetails() {
    System.out.println("please enter a id :");
    System.out.println(computerService.get(scanIn.nextLong()).toString());
  }

  /**
   * Update.
   */
  private void update() {
    System.out.println("please enter a id :");
    Computer computer = computerService.get(scanIn.nextLong());
    System.out.println("please enter a new name :");
    scanIn.nextLine(); // empty previous line
    computer.setName(scanIn.nextLine());

    System.out
        .println("please enter date when the computer was introduced (date format : yyyy/MM/dd) :");
    computer.setIntroduced(waitForValidDate(computer));
    System.out.println(
        "please enter date when the computer was discontinued (date format : yyyy/MM/dd) :");
    computer.setDiscontinued(waitForValidDate(computer));
    System.out.println("please enter id of manufacturer :");

    Company company = new Company();
    company.setId(scanIn.nextLong());
    computer.setCompany(company);

    try {
      computerService.update(computer);
      System.out
          .println("computer " + computer.getId() + " has been succesfully updated to database");
    } catch (ServiceValidatorException e) {
      System.out.println("computer " + computer.getId()
          + " was NOT updated in database because the dates are not correct");
    }
  }

  /**
   * Delete single computer.
   */
  private void deleteSingleComputer() {
    System.out.println("please enter a id :");
    long id = scanIn.nextLong();
    computerService.delete(id);
    System.out.println("computer " + id + " has been succesfully deleted from database");
  }

  /**
   * Delete by company id.
   */
  private void deleteByCompanyId() {
    System.out.println("please enter a id :");
    long id = scanIn.nextLong();
    companyService.delete(id);
    System.out.println("company " + id
        + ", and all the related computers have been succesfully deleted from database");
  }

  /**
   * Wait for valid date.
   *
   * @param computer
   *          the computer
   * @return the local date
   */
  private LocalDate waitForValidDate(Computer computer) {

    LocalDate dateTime = null;

    while (dateTime == null) {
      String sdate = scanIn.nextLine();
      try {
        dateTime = LocalDate.parse(sdate, formatter);
      } catch (DateTimeParseException e) {
        dateTime = null;
        System.out.println("date not valid, try again");
      }
    }
    return dateTime;
  }
}

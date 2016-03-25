package com.gnostrenoff.cdb.cli.listener;

import com.gnostrenoff.cdb.cli.util.CompanyCli;
import com.gnostrenoff.cdb.cli.util.ComputerCli;
import com.gnostrenoff.cdb.cli.util.PageCli;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * The Class Listener.
 */
public class Listener {

    /**
     * The Constant scanIn.
     */
    public static final Scanner scanIn = new Scanner(System.in);

    /**
     * The Constant formatter.
     */
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static Client client = ClientBuilder.newClient().register(JacksonFeature.class);
    private static WebTarget computerWebTarget = client.target("http://localhost:8080/api/computer");
    private static WebTarget companyWebTarget = client.target("http://localhost:8080/api/company");

    private int pageIndex = 1;

    /**
     * The exit.
     */
    public static boolean exit = false;

    /**
     * The input.
     */
    public static int input;

    /**
     * Listen.
     */
    public void listen() {

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
        System.out.println("\t==============================================");
        System.out.println("\t|   SELECT YOUR OPTION                       |");
        System.out.println("\t==============================================");
        System.out.println("\t| Options:                                   |");
        System.out.println("\t|        1. list all companies               |");
        System.out.println("\t|        2. list all computers               |");
        System.out.println("\t|        3. create computer                  |");
        System.out.println("\t|        4. read detail computer             |");
        System.out.println("\t|        5. update computer                  |");
        System.out.println("\t|        6. delete computer                  |");
        System.out.println("\t|        7. delete computer(s) by company id |");
        System.out.println("\t|        8. exit                             |");
        System.out.println("\t==============================================");
    }

    /**
     * List companies.
     */
    private void listCompanies() {
        List<CompanyCli> companiesList = companyWebTarget.request().accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<CompanyCli>>() {
                });
        companiesList.forEach(company -> System.out.println(company));
    }

    /**
     * List computers.
     */
    private void listComputers() {

        int response;
        pageIndex = 0;

        do {
            pageIndex++;
            PageCli page = computerWebTarget.path("all/" + pageIndex).request().accept(MediaType.APPLICATION_JSON)
                    .get(PageCli.class);

            page.getComputerList().forEach(computer -> System.out.println(computer));

            if (page.getNbTotalPages() == pageIndex) {
                System.out.println("No more computer.");
                return;
            }

            System.out.println("Would you like the next page ? (1/0)");
            response = scanIn.nextInt();
        } while (response == 1);

    }

    /**
     * Creates the.
     */
    private void create() {

        String name;
        String introduced;
        String discontinued;
        long companyId;

        System.out.println("please enter a name :");
        scanIn.nextLine(); // empty previous line
        name = scanIn.nextLine();

        System.out.println("please enter date when the computer was introduced (date format : yyyy-MM-dd) :");
        introduced = waitForValidDate();
        System.out.println("please enter date when the computer was introduced (date format : yyyy-MM-dd) :");
        discontinued = waitForValidDate();

        System.out.println("please enter id of manufacturer :");
        companyId = scanIn.nextLong();

        ComputerCli computerCli = new ComputerCli(name, introduced, discontinued, companyId);

        Response response = computerWebTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(computerCli, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            System.out.println("An error occured while requesting web service :");
            System.out.println(response.toString());
        } else {
            System.out.println("Computer were successfully added to database !");
        }
    }

    /**
     * Read details.
     */
    private void readDetails() {

        System.out.println("please enter a id :");
        long id = scanIn.nextLong();

        ComputerCli computer = computerWebTarget.path("/" + id).request().accept(MediaType.APPLICATION_JSON)
                .get(ComputerCli.class);
        System.out.println(computer);
    }

    /**
     * Update.
     */
    private void update() {

        System.out.println("please enter a id :");
        long id = scanIn.nextLong();

        ComputerCli computer = computerWebTarget.path("/" + id).request().accept(MediaType.APPLICATION_JSON)
                .get(ComputerCli.class);

        if (computer == null) {
            System.out.println("Computer not found.");
            return;
        }

        System.out.println("please enter a name :");
        scanIn.nextLine(); // empty previous line
        computer.setName(scanIn.nextLine());

        System.out.println("please enter date when the computer was introduced (date format : yyyy-MM-dd) :");
        computer.setIntroduced(waitForValidDate());
        System.out.println("please enter date when the computer was introduced (date format : yyyy-MM-dd) :");
        computer.setDiscontinued(waitForValidDate());

        System.out.println("please enter id of manufacturer :");
        computer.setCompanyId(scanIn.nextLong());

        Response response = computerWebTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(computer, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            System.out.println("An error occured while requesting web service, are you sure this computer exists ?");
            System.out.println(response.toString());
        } else {
            System.out.println("Computer " + id + " were successfully updated !");
        }
    }

    /**
     * Delete single computer.
     */
    private void deleteSingleComputer() {
        System.out.println("please enter a id :");
        long id = scanIn.nextLong();

        Response response = computerWebTarget.path("/" + id).request().delete();

        if (response.getStatus() != 200) {
            System.out.println("An error occured while requesting web service, are you sure this computer exists ?");
            System.out.println(response.toString());
        } else {
            System.out.println("Computer " + id + " were successfully deleted !");
        }
    }

    /**
     * Delete by company id.
     */
    private void deleteByCompanyId() {

        System.out.println("please enter a company id :");
        long id = scanIn.nextLong();
        System.out.println("careful ! this action will delete all computers associated to this company as well, continue anyway ?");
        if(scanIn.nextInt() == 1) {
            companyWebTarget.path("/" + id).request().delete();
            System.out.println("company and its computers were successfully deleted.");
        } else {
            System.out.println("operation cancelled");
        }
    }

    /**
     * Wait for valid date.
     *
     * @return the local date
     */
    private String waitForValidDate() {

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
        return dateTime.format(formatter);
    }
}

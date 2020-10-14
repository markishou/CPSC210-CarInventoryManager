package ui;

import model.Vehicle;
import model.Vehicles;

import java.util.List;
import java.util.Scanner;

// Car inventory application with a console based ui
public class CarInventoryApp {

    private Vehicle listedCar;
    private Vehicle listedCar2;
    private Vehicle listedCar3;

    private Vehicles carInventory;
    private Scanner input;

    // EFFECTS: runs the car inventory app
    public CarInventoryApp() {
        runInventory();
    }

    // ***template for this ui is from the TellerApp project***

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runInventory() {

        boolean running = true;
        String command = null;

        init();

        while (running) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you later.");
    }

    // MODIFIES: this
    // EFFECTS: initializes inventory and input
    private void init() {

        carInventory = new Vehicles();

        listedCar = new Vehicle(2002, 190000, "acura",
                "rsx type s", "clean", true);
        listedCar2 = new Vehicle(2004, 100000, "acura",
                "rsx type s", "clean", false);
        listedCar3 = new Vehicle(2002, 80000, "nissan",
                "silvia s15", "clean", true);

        carInventory.listToAllListings(listedCar);
        carInventory.listToAllListings(listedCar2);
        carInventory.listToAllListings(listedCar3);
        input = new Scanner(System.in);
    }

    // EFFECTS: displays current available inventory
    private void displayInventory() {

        System.out.println("Current Inventory: ");
        List<String> currentInventory = carInventory.allListingsToString();

        for (String s: currentInventory) {
            System.out.println(s);
        }
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {

        System.out.println("\nSelect from:");
        System.out.println("\t1 -> search inventory");
        System.out.println("\t2 -> view inventory");
        System.out.println("\t3 -> list my car for sale");
        System.out.println("\t4 -> view my listings");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            searchInventory();
        } else if (command.equals("2")) {
            displayInventory();
        } else if (command.equals("3")) {
            listCar();
        } else if (command.equals("4")) {
            viewMyListings();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // REQUIRES: input must be lower case
    // EFFECTS: displays matches for search
    private void searchInventory() {

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the range of years for the car you are looking for");
        System.out.println("Start with the oldest year: ");
        int startYear = input.nextInt();
        System.out.println("Now enter the latest year: ");
        int endYear = input.nextInt();

        System.out.println("Enter the manufacturer: ");
        String manufacturer = s.nextLine();
        System.out.println("Enter the model: ");
        String model = s.nextLine();

        List<Vehicle> matches = carInventory.searchListings(startYear, endYear, manufacturer, model);

        if (matches.size() > 0) {
            System.out.println("Here are some matches: ");
            for (Vehicle v : matches) {
                System.out.println(v.toString());
            }
        } else {
            System.out.println("Sorry, there are no matches for this vehicle");
        }
    }

    // REQUIRES: input must be lower case
    // MODIFIES: this
    // EFFECTS: lists car for sale with user inputted specifications
    private void listCar() {

        Vehicle newListing;
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the year of the vehicle: ");
        int year = input.nextInt();
        System.out.println("Enter the mileage: ");
        int odometer = input.nextInt();

        System.out.println("Enter the manufacturer: ");
        String manufacturer = s.nextLine();
        System.out.println("Enter the model: ");
        String model = s.nextLine();
        System.out.println("Enter the title: ");
        System.out.println("Either 'clean' or 'rebuilt'");
        String title = s.nextLine();

        System.out.println("Is the car modified: ");
        System.out.println("\t1 -> yes");
        System.out.println("\tAny char -> no");
        boolean stock;
        String command = input.next();
        stock = processStockOrNot(command);

        newListing = new Vehicle(year, odometer, manufacturer, model, title, stock);
        carInventory.listVehicle(newListing);
    }

    // EFFECTS: prints out the users listings
    private void viewMyListings() {

        List<String> myListings = carInventory.myListingsToString();
        System.out.println("My listings: ");
        for (String s: myListings) {
            System.out.println(s);
        }

    }

    // EFFECTS: processes user input for if a car is stock or not
    private boolean processStockOrNot(String command) {
        if (command.equals("1")) {
            return false;
        } else {
            return true;
        }
    }


}
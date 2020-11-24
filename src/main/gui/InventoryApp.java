package gui;

import exceptions.NegativeMileage;
import model.Vehicle;
import model.Vehicles;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Graphical interface for a car inventory.
public class InventoryApp extends JFrame implements ActionListener {

    private Vehicles inventory;
    private Vehicle listedCar;
    private Vehicle listedCar2;
    private Vehicle listedCar3;

    private static final String JSON_STORE = "./data/myListings.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    private JList<String> vehicleJList;
    private DefaultListModel<String> listModel;

    private JButton listVehicle;
    private JButton viewMyListings;
    private JButton saveMyListings;
    private JButton loadMyListings;
    private JButton viewAllListings;

    private JTextField yearField = new JTextField(10);
    private JTextField odometerField = new JTextField(10);
    private JTextField manufacturerField = new JTextField(10);
    private JTextField modelField = new JTextField(10);
    private JTextField titleField = new JTextField(10);
    private JTextField stockField = new JTextField(10);

    private JLabel yearLabel = new JLabel("Enter the year:");
    private JLabel odometerLabel = new JLabel("Enter the mileage:");
    private JLabel manufacturerLabel = new JLabel("Enter the manufacturer:");
    private JLabel modelLabel = new JLabel("Enter the model");
    private JLabel titleLabel = new JLabel("clean or rebuilt:");
    private JLabel stockLabel = new JLabel("Is it modified: 1 -> yes | Any char -> no");

    //private String soundFile = "./Sound/Button.wav";


    // EFFECTS: opens a Car inventory gui
    public InventoryApp() {
        super("Car Inventory");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));
        createInventory();
        createInputLayout();
        createButtonLayout();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: creates a scrollable pane for existing car inventory
    public void createInventory() {
        try {
            listedCar = new Vehicle(2002, 190000, "acura",
                    "rsx type s", "clean", true);
            listedCar2 = new Vehicle(2004, 100000, "acura",
                    "rsx type s", "clean", false);
            listedCar3 = new Vehicle(2002, 80000, "nissan",
                    "silvia s15", "clean", true);
        } catch (NegativeMileage negativeMileage) {
            System.out.println("Mileage can not be negative...");
        }

        inventory = new Vehicles();
        inventory.listToAllListings(listedCar);
        inventory.listToAllListings(listedCar2);
        inventory.listToAllListings(listedCar3);

        listModel = new DefaultListModel();

        for (Vehicle v: inventory.getAllListings()) {
            listModel.addElement(v.toString());
        }

        vehicleJList = new JList<>(listModel);

        vehicleJList.setVisibleRowCount(5);
        JScrollPane listPane = new JScrollPane(vehicleJList);
        add(listPane, BorderLayout.WEST);
    }

    // EFFECTS: Creates a layout for buttons used in the gui
    // used ListDemo as a reference: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
    public void createButtonLayout() {
        listVehicle = new JButton("List Vehicle");
        listVehicle.addActionListener(this);

        viewMyListings = new JButton("View my listings");
        viewMyListings.addActionListener(new MyListingsListener());

        viewAllListings = new JButton("View all listings");
        viewAllListings.addActionListener(new AllListingsListener());

        saveMyListings = new JButton("Save my listings");
        saveMyListings.addActionListener(new SaveListingsListener());

        loadMyListings = new JButton("Load my listings");
        loadMyListings.addActionListener(new LoadListingsListener());

        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(Color.lightGray);
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
        buttonPane.add(listVehicle);
        buttonPane.add(viewMyListings);
        buttonPane.add(viewAllListings);
        buttonPane.add(saveMyListings);
        buttonPane.add(loadMyListings);
        add(buttonPane);
    }

    // EFFECTS: creates a text field layout for vehicle specification input
    public void createInputLayout() {
        JPanel inputPane = new JPanel();
        inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.Y_AXIS));
        inputPane.add(yearLabel);
        inputPane.add(yearField);
        inputPane.add(odometerLabel);
        inputPane.add(odometerField);
        inputPane.add(manufacturerLabel);
        inputPane.add(manufacturerField);
        inputPane.add(modelLabel);
        inputPane.add(modelField);
        inputPane.add(titleLabel);
        inputPane.add(titleField);
        inputPane.add(stockLabel);
        inputPane.add(stockField);
        add(inputPane, BorderLayout.BEFORE_FIRST_LINE);
    }

    //EFFECTS: creates a new JFrame and displays an image
    public void displayImage(String fileName) {
        JFrame listedImage = new JFrame();
        listedImage.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        listedImage.setPreferredSize(new Dimension(300, 300));
        ImageIcon image = new ImageIcon(fileName);
        JLabel imageLabel = new JLabel(image);
        listedImage.add(imageLabel);
        listedImage.pack();
        listedImage.setLocationRelativeTo(null);
        listedImage.setVisible(true);
    }

    // EFFECTS: lists vehicle based on the inputted specifications
    @Override
    public void actionPerformed(ActionEvent e) {
        int year = Integer.parseInt(yearField.getText());
        int odometer = Integer.parseInt(odometerField.getText());
        String manufacturer = manufacturerField.getText();
        String model = modelField.getText();
        String title = titleField.getText();
        boolean stock = stockToBool(stockField.getText());

        Vehicle vehicle = null;

        try {
            vehicle = new Vehicle(year, odometer, manufacturer, model, title, stock);
        } catch (NegativeMileage negativeMileage) {
            System.out.println("Mileage can not be negative...");
        }

        inventory.listVehicle(vehicle);
        listModel.addElement(vehicle.toString());

        displayImage("./images/success.jpg");

        yearField.setText("");
        odometerField.setText("");
        manufacturerField.setText("");
        modelField.setText("");
        titleField.setText("");
        stockField.setText("");
    }

    // EFFECTS: converts stock field input to boolean
    private boolean stockToBool(String s) {
        if (s.equals("1")) {
            return false;
        } else {
            return true;
        }
    }

    // action listener for viewing user's listings
    class MyListingsListener implements ActionListener {

        // EFFECTS: displays the user's listings
        @Override
        public void actionPerformed(ActionEvent e) {
            listModel.clear();
            for (Vehicle v: inventory.getMyListings()) {
                listModel.addElement(v.toString());
            }
        }
    }

    // action listener for viewing all listings
    class AllListingsListener implements ActionListener {

        // EFFECTS: displays all listings
        @Override
        public void actionPerformed(ActionEvent e) {
            listModel.clear();
            for (Vehicle v: inventory.getAllListings()) {
                listModel.addElement(v.toString());
            }
        }
    }

    // action listener for saving user's listings
    class SaveListingsListener implements ActionListener {

        // EFFECTS: saves users' listings to Json file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(inventory);
                jsonWriter.close();
                displayImage("./images/saved.jpg");
                System.out.println("Saved your listings to " + JSON_STORE);
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // action listener for loading user's listings
    class LoadListingsListener implements ActionListener {

        // EFFECTS: loads user's listings from Json file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                inventory = jsonReader.read();

                listedCar = new Vehicle(2002, 190000, "acura",
                        "rsx type s", "clean", true);
                listedCar2 = new Vehicle(2004, 100000, "acura",
                        "rsx type s", "clean", false);
                listedCar3 = new Vehicle(2002, 80000, "nissan",
                        "silvia s15", "clean", true);

                inventory.listToAllListings(listedCar);
                inventory.listToAllListings(listedCar2);
                inventory.listToAllListings(listedCar3);

                listModel.clear();

                for (Vehicle v: inventory.getAllListings()) {
                    listModel.addElement(v.toString());
                }

                displayImage("./images/loaded.jpg");

                System.out.println("Loaded your listings from " + JSON_STORE);
            } catch (IOException ex) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            } catch (NegativeMileage nm) {
                System.out.println("Mileage can not be negative...");
            }
        }
    }

    public static void main(String[] args) {
        new InventoryApp();
    }
}

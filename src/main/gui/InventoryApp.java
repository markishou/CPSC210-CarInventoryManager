package gui;

import model.Vehicle;
import model.Vehicles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Graphical interface for a car inventory.
public class InventoryApp extends JFrame implements ActionListener {

    private Vehicles inventory;
    private Vehicle listedCar;
    private Vehicle listedCar2;
    private Vehicle listedCar3;

    private JList<String> vehicleJList;
    private DefaultListModel<String> listModel;

    private JButton listVehicle;
    private JButton viewMyListings;

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
    private JLabel inventoryLabel = new JLabel("Current Inventory");


    // EFFECTS: opens a Car inventory gui
    public InventoryApp() {
        super("Car Inventory");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        listedCar = new Vehicle(2002, 190000, "acura",
                "rsx type s", "clean", true);
        listedCar2 = new Vehicle(2004, 100000, "acura",
                "rsx type s", "clean", false);
        listedCar3 = new Vehicle(2002, 80000, "nissan",
                "silvia s15", "clean", true);
        inventory = new Vehicles();
        inventory.listToAllListings(listedCar);
        inventory.listToAllListings(listedCar2);
        inventory.listToAllListings(listedCar3);
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
    // reference from ListDemo: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
    public void createButtonLayout() {
//        AddVehicleListener addVehicleListener = new AddVehicleListener();
        listVehicle = new JButton("List Vehicle");
        listVehicle.addActionListener(this);

        viewMyListings = new JButton("View my listings");
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
        buttonPane.add(listVehicle);
        buttonPane.add(viewMyListings);
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
        inputPane.add(inventoryLabel);
        add(inputPane, BorderLayout.BEFORE_FIRST_LINE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int year = Integer.parseInt(yearField.getText());
        int odometer = Integer.parseInt(odometerField.getText());
        String manufacturer = manufacturerField.getText();
        String model = modelField.getText();
        String title = titleField.getText();
        boolean stock = stockToBool(stockField.getText());

        Vehicle vehicle = new Vehicle(year, odometer, manufacturer, model, title, stock);
        inventory.listVehicle(vehicle);
        listModel.addElement(vehicle.toString());

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

    public static void main(String[] args) {
        new InventoryApp();
    }
}

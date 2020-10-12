package model;

import java.util.ArrayList;
import java.util.List;

public class Vehicles {

    private List<Vehicle> allListings;
    private List<Vehicle> myListings;

    // EFFECTS: inventory is empty
    public Vehicles() {
        allListings = new ArrayList<Vehicle>();
        myListings = new ArrayList<Vehicle>();
    }

    // MODIFIES: this
    // EFFECTS: lists a vehicle to allListings and myListings
    public void listVehicle(Vehicle v) {
        allListings.add(v);
        myListings.add(v);
    }

    // MODIFIES: this
    // EFFECTS: lists a vehicle to allListings
    public void listToAllListings(Vehicle v) {
        allListings.add(v);
    }

    // REQUIRES: maker and model must be lowercase
    // EFFECTS: returns a list of cars from the inventory that matches the search
    public List<Vehicle> searchListings(int startYear, int endYear, String maker, String model) {

        List<Vehicle> matches = new ArrayList<Vehicle>();

        for (Vehicle v: allListings) {
            if (v.getYear() >= startYear && v.getYear() <= endYear && v.getManufacturer() == maker && v.getModel() == model) {
                matches.add(v);
            }
        }

        return matches;
    }

    // EFFECTS: returns the size of the inventory
    public int getAllListingsSize() {
        return allListings.size();
    }

    // EFFECTS: returns the number of cars you listed
    public int getMyListingsSize() {
        return myListings.size();
    }
}

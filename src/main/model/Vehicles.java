package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// a class that represents all listings available as well as all of the user's listings
public class Vehicles implements Writable {

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
    public List<Vehicle> searchListings(int startYear, int endYear, String manufacturer, String model) {

        List<Vehicle> matches = new ArrayList<Vehicle>();

        for (Vehicle v: allListings) {
            if (v.getYear() >= startYear && v.getYear() <= endYear && manufacturer.equals(v.getManufacturer())
                    && model.equals(v.getModel())) {
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

    // EFFECTS: returns a list of vehicles from allListings reformatted to String
    public List<String> allListingsToString() {

        List<String> stringVehicles = new ArrayList<>();

        for (Vehicle v: allListings) {
            stringVehicles.add(v.toString());
        }

        return stringVehicles;
    }

    // EFFECTS: returns a list of vehicles from allListings reformatted to String
    public List<String> myListingsToString() {

        List<String> stringVehicles = new ArrayList<>();

        for (Vehicle v: myListings) {
            stringVehicles.add(v.toString());
        }

        return stringVehicles;
    }

    // EFFECTS: returns myListings as an unmodifiable list
    public List<Vehicle> getMyListings() {
        return Collections.unmodifiableList(myListings);
    }

    // EFFECTS: returns allListings
    public List<Vehicle> getAllListings() {
        return allListings;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("name", "My Listings");
        jsonObject.put("vehicles", vehiclesToJson());
        return jsonObject;
    }

    // *** from JsonSerializationDemo WorkRoom method
    // EFFECTS: returns vehicles in my listings as a JSON Array
    private JSONArray vehiclesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Vehicle v: myListings) {
            jsonArray.put(v.toJson());
        }

        return jsonArray;
    }
}

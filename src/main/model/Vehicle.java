package model;

import org.json.JSONObject;
import persistance.Writable;

// Vehicle represents a vehicle object with a year, odometer reading, manufacturer, model, and title. It also represents
// whether the vehicle is stock or not as well as if it has been sold.
public class Vehicle implements Writable {

    private int year;
    private int odometer;
    private String manufacturer;
    private String model;
    private String title;
    private boolean stock;
    private boolean sold = false;

    // REQUIRES: odometer >= 0, title must be either "clean" or "rebuilt", all string inputs must be lowercase
    // EFFECTS: creates a vehicle with the passed in specifications.
    public Vehicle(int year, int odometer, String manufacturer, String model, String title, boolean stock) {
        this.year = year;
        this.odometer = odometer;
        this.manufacturer = manufacturer;
        this.model = model;
        this.title = title;
        this.stock = stock;
    }

    public int getYear() {
        return this.year;
    }

    public int getOdometer() {
        return this.odometer;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean getStock() {
        return this.stock;
    }

    public boolean getSold() {
        return this.sold;
    }

    // REQUIRES: sold is not true
    // EFFECTS: vehicle listing is sold
    public void setSold() {
        this.sold = true;
    }

    // EFFECTS: if stock is true then return "stock", otherwise return "modified"
    public String stockToString() {
        if (stock) {
            return "stock";
        } else {
            return "modified";
        }
    }

    // EFFECTS: if sold is true return "sold", otherwise return "available"
    public String soldToString() {
        if (!sold) {
            return "available";
        } else {
            return "sold";
        }
    }

    // EFFECTS: constructs a string that represents a vehicle
    public String toString() {
        return year + " " + manufacturer + " " + model + ", " + title + " title, " + odometer + "km, "
                + stockToString() + ", " + soldToString();
    }

    @Override
    public JSONObject toJson() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("year", year);
        jsonObject.put("odometer", odometer);
        jsonObject.put("manufacturer", manufacturer);
        jsonObject.put("model", model);
        jsonObject.put("title", title);
        jsonObject.put("stock", stock);

        return jsonObject;
    }
}


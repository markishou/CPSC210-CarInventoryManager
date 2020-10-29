package persistance;

import model.Vehicle;
import model.Vehicles;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// ***JsonReader model is pulled from the JsonSerializationDemo
// JsonReader is a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Vehicles from file and returns it; throws IOException if an error occurs reading data from file
    public Vehicles read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseVehicles(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Vehicles from JSON object and returns it
    private Vehicles parseVehicles(JSONObject jsonObject) {
        Vehicles vehicles = new Vehicles();
        addVehicles(vehicles, jsonObject);
        return vehicles;
    }

    // MODIFIES: vehicles
    // EFFECTS: parses vehicles from JSON object and adds them to list of vehicles
    private void addVehicles(Vehicles vehicles, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("vehicles");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addVehicle(vehicles, nextThingy);
        }
    }

    // MODIFIES: vehicles
    // EFFECTS: parses vehicle from JSON object and adds it to vehicles
    private void addVehicle(Vehicles vehicles, JSONObject jsonObject) {
        int year = jsonObject.getInt("year");
        int odometer = jsonObject.getInt("odometer");
        String manufacturer = jsonObject.getString("manufacturer");
        String model = jsonObject.getString("model");
        String title = jsonObject.getString("title");
        boolean stock = jsonObject.getBoolean("stock");
        Vehicle v = new Vehicle(year,odometer,manufacturer,model,title,stock);
        vehicles.listVehicle(v);
    }

}

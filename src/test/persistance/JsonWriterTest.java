package persistance;

import exceptions.NegativeMileage;
import model.Vehicle;
import model.Vehicles;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// *** Test template pulled from JsonSerializationDemo
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile(){
        try {
            Vehicles vehicles = new Vehicles();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected\"");
        } catch (IOException e) {
            // success
        }
    }

    @Test
    void testWriterEmptyVehicles() {
        try {
            Vehicles vehicles = new Vehicles();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyVehicles.json");
            writer.open();
            writer.write(vehicles);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyVehicles.json");
            vehicles = reader.read();
            assertEquals(0, vehicles.getMyListingsSize());
        } catch (IOException e) {
            fail("exception should not have been thrown");
        }

    }

    @Test
    void testWriterGeneralVehicles() {
        try {
            Vehicles vehicles = new Vehicles();
            try {
                vehicles.listVehicle(new Vehicle(2002, 190000, "acura",
                        "rsx type s", "clean", true));
                vehicles.listVehicle(new Vehicle(2004, 100000, "acura",
                        "rsx type s", "clean", true));
            } catch (NegativeMileage negativeMileage) {
                negativeMileage.printStackTrace();
            }
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralVehicles.json");
            writer.open();
            writer.write(vehicles);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralVehicles.json");
            vehicles = reader.read();
            List<Vehicle> vehicleList = vehicles.getMyListings();
            assertEquals(2, vehicleList.size());
            sameVehicle(2002, 190000, "acura",
                    "rsx type s", "clean", true, vehicleList.get(0));
            sameVehicle(2004, 100000, "acura",
                    "rsx type s", "clean", true, vehicleList.get(1));

        } catch (IOException e) {
            fail("exception was thrown");
        }
    }

}

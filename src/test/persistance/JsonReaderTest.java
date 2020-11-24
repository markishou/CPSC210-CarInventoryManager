package persistance;

import exceptions.NegativeMileage;
import model.Vehicle;
import model.Vehicles;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// *** Test template pulled from JsonSerializationDemo
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Vehicles vehicles = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        } catch (NegativeMileage nm) {
            fail("wrong exception");
        }
    }

    @Test
    void testReaderEmptyVehicles() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyVehicles.json");
        try {
            Vehicles vehicles = reader.read();
            assertEquals(0, vehicles.getMyListingsSize());
        } catch (IOException e){
            fail("exception was thrown");
        } catch (NegativeMileage nm) {
            fail("exception was thrown");
        }
    }

    @Test
    void testReaderGeneralVehicles() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralVehicles.json");
        try {
            Vehicles vehicles = reader.read();
            List<Vehicle> myLists = vehicles.getMyListings();
            assertEquals(2, myLists.size());
            sameVehicle(2002, 190000, "acura",
                    "rsx type s", "clean", true, myLists.get(0));
            sameVehicle(2004, 100000, "acura",
                    "rsx type s", "clean", true, myLists.get(1));
        } catch (IOException e){
            fail("exception was thrown");
        } catch (NegativeMileage nm) {
            fail("exception was thrown");
        }
    }

    @Test
    void testReaderGeneralVehiclesNegativeMileage() {
        JsonReader reader = new JsonReader("./data/testReaderNegativeMileage.json");
        try {
            Vehicles vehicles = reader.read();
            List<Vehicle> myLists = vehicles.getMyListings();
            assertEquals(2, myLists.size());
            sameVehicle(2002, 190000, "acura",
                    "rsx type s", "clean", true, myLists.get(0));
            sameVehicle(2004, 100000, "acura",
                    "rsx type s", "clean", true, myLists.get(1));
            fail();
        } catch (NegativeMileage nm){
            //pass
        } catch (IOException e) {
            // pass
        }
    }
}

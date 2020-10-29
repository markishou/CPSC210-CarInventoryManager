package persistance;

import model.Vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void sameVehicle(int year, int odometer, String manufacturer, String model, String title,
                               boolean stock, Vehicle v) {
        assertEquals(year, v.getYear());
        assertEquals(odometer, v.getOdometer());
        assertEquals(manufacturer, v.getManufacturer());
        assertEquals(model, v.getModel());
        assertEquals(title, v.getTitle());
        assertEquals(stock, v.getStock());
    }

}

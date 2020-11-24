package model;

import exceptions.NegativeMileage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    Vehicle vehicle;

    @BeforeEach
    void runBefore() {

        try {
            vehicle = new Vehicle(2002, 190000,"acura",
                    "rsx type s", "clean", true);
        } catch (NegativeMileage negativeMileage) {
            System.out.println("Mileage can not be negative...");
        }
    }

    @Test
    void testConstructorContents() {

        assertEquals(2002, vehicle.getYear());
        assertEquals(190000, vehicle.getOdometer());
        assertEquals("acura", vehicle.getManufacturer());
        assertEquals("rsx type s", vehicle.getModel());
        assertEquals("clean", vehicle.getTitle());
        assertTrue(vehicle.getStock());
        assertFalse(vehicle.getSold());
    }

    @Test
    void testConstructorExceptionThrown() {
        try {
            Vehicle v = new Vehicle(2002, -90000,"acura",
                    "rsx type s", "clean", true);
            fail();
        } catch (NegativeMileage negativeMileage) {
            // Success
        }
    }

    @Test
    void testConstructorExceptionNotThrown() {
        try {
            Vehicle v = new Vehicle(2002, 90000,"acura",
                    "rsx type s", "clean", true);
            // success
        } catch (NegativeMileage negativeMileage) {
            fail();
        }
    }

    @Test
    void testSetSold() {

        vehicle.setSold();
        assertTrue(vehicle.getSold());
    }

    @Test
    void testStockToStringStock() {

        assertEquals("stock", vehicle.stockToString());
    }

    @Test
    void testStockToStringMod() {

        try {
            vehicle = new Vehicle(2002, 190000,"acura",
                    "rsx type s", "clean", false);
        } catch (NegativeMileage negativeMileage) {
            System.out.println("Mileage can not be negative...");
        }

        assertEquals("modified", vehicle.stockToString());
    }

    @Test
    void testSoldToStringAvailable() {

        assertEquals("available", vehicle.soldToString());
    }

    @Test
    void testSoldToStringSold() {

        vehicle.setSold();
        assertEquals("sold", vehicle.soldToString());
    }

    @Test
    void testToString() {

        assertEquals("2002 acura rsx type s, clean title, 190000km, stock, available", vehicle.toString());
    }
}
package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    Vehicle vehicle;

    @BeforeEach
    void runBefore() {
        vehicle = new Vehicle(2002, 190000,"acura",
                "rsx type s", "clean", true);
    }

    @Test
    void testConstructor() {
        assertEquals(2002, vehicle.getYear());
        assertEquals(190000, vehicle.getOdometer());
        assertEquals("acura", vehicle.getManufacturer());
        assertEquals("rsx type s", vehicle.getModel());
        assertEquals("clean", vehicle.getTitle());
        assertTrue(vehicle.getStock());
        assertFalse(vehicle.getSold());
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
        vehicle = new Vehicle(2002, 190000,"acura",
                "rsx type s", "clean", false);

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
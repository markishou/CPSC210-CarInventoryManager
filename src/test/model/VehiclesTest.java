package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehiclesTest {

    Vehicles vehicles;
    Vehicle vehicle;

    @BeforeEach
    void runBefore() {

        vehicles = new Vehicles();
        vehicle = new Vehicle(2002, 190000,"acura",
                "rsx type s", "clean", true);
    }

    @Test
    void testConstructor() {

        assertEquals(0, vehicles.getAllListingsSize());
        assertEquals(0, vehicles.getMyListingsSize());
    }

    @Test
    void testListVehicle() {

        vehicles.listVehicle(vehicle);
        assertEquals(1,vehicles.getAllListingsSize());
        assertEquals(1,vehicles.getMyListingsSize());
    }

    @Test
    void testListToAllListings() {

        vehicles.listToAllListings(vehicle);
        assertEquals(1,vehicles.getAllListingsSize());
    }

    @Test
    void testSearchListingsMatch() {

        vehicles.listVehicle(vehicle);
        List<Vehicle> matches = vehicles.searchListings(2002, 2004,
                "acura", "rsx type s");

        assertEquals(1, matches.size());
        assertEquals(vehicle, matches.get(0));
    }

    @Test
    void testSearchListingsMultiMatches() {

        Vehicle vehicle1 = new Vehicle(2004, 100000,"acura",
                "rsx type s", "clean", true);

        Vehicle vehicle2 = new Vehicle(2006, 100000,"acura",
                "rsx type s", "clean", true);

        vehicles.listVehicle(vehicle);
        vehicles.listVehicle(vehicle1);
        vehicles.listVehicle(vehicle2);

        List<Vehicle> matches = vehicles.searchListings(2002, 2004,
                "acura", "rsx type s");

        assertEquals(2, matches.size());
        assertEquals(vehicle, matches.get(0));
        assertEquals(vehicle1, matches.get(1));
    }

    @Test
    void testSearchListingsCloseMatches() {

        Vehicle vehicle1 = new Vehicle(2004, 100000,"acura",
                "rsx type s", "clean", true);

        Vehicle vehicle2 = new Vehicle(2003, 100000,"honda",
                "civic", "clean", true);

        Vehicle vehicle3 = new Vehicle(2003, 100000,"acura",
                "tsx", "clean", true);

        vehicles.listVehicle(vehicle);
        vehicles.listVehicle(vehicle1);
        vehicles.listVehicle(vehicle2);
        vehicles.listVehicle(vehicle3);

        List<Vehicle> matches = vehicles.searchListings(2002, 2003,
                "acura", "rsx type s");

        assertEquals(1, matches.size());
        assertEquals(vehicle, matches.get(0));
    }

    @Test
    void testSearchListingsNoMatch() {

        vehicles.listVehicle(vehicle);
        List<Vehicle> matches = vehicles.searchListings(2005,2006,
                "acura", "rsx type s");

        assertEquals(0, matches.size());
    }

    @Test
    void testAllListingsToStringOne() {

        vehicles.listVehicle(vehicle);
        List<String> stringVehicles = vehicles.allListingsToString();

        assertEquals("2002 acura rsx type s, clean title, 190000km, stock, available", stringVehicles.get(0));
    }

    @Test
    void testAllListingsToStringMulti() {

        Vehicle vehicle1 = new Vehicle(2004, 100000,"acura",
                "rsx type s", "clean", true);

        vehicles.listVehicle(vehicle);
        vehicles.listVehicle(vehicle1);

        List<String> stringVehicles = vehicles.allListingsToString();

        assertEquals("2002 acura rsx type s, clean title, 190000km, stock, available", stringVehicles.get(0));
        assertEquals("2004 acura rsx type s, clean title, 100000km, stock, available", stringVehicles.get(1));
    }

    @Test
    void testAllListingsToStringEmpty() {

        List<String> stringVehicles = vehicles.allListingsToString();

        assertEquals(0, stringVehicles.size());
    }

    @Test
    void testMyListingsToStringOne() {

        vehicles.listVehicle(vehicle);
        List<String> stringVehicles = vehicles.myListingsToString();

        assertEquals("2002 acura rsx type s, clean title, 190000km, stock, available", stringVehicles.get(0));
    }

    @Test
    void testMyListingsToStringMulti() {

        Vehicle vehicle1 = new Vehicle(2004, 100000,"acura",
                "rsx type s", "clean", true);

        vehicles.listVehicle(vehicle);
        vehicles.listVehicle(vehicle1);

        List<String> stringVehicles = vehicles.myListingsToString();

        assertEquals("2002 acura rsx type s, clean title, 190000km, stock, available", stringVehicles.get(0));
        assertEquals("2004 acura rsx type s, clean title, 100000km, stock, available", stringVehicles.get(1));
    }

    @Test
    void testMyListingsToStringEmpty() {

        List<String> stringVehicles = vehicles.myListingsToString();

        assertEquals(0, stringVehicles.size());
    }
}

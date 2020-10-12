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
    void testSearchListingsNoMatch() {

        vehicles.listVehicle(vehicle);
        List<Vehicle> matches = vehicles.searchListings(2005,2006,
                "acura", "rsx type s");

        assertEquals(0, matches.size());
    }
}

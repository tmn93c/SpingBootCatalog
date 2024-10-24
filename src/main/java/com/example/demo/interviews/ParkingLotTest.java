package com.example.demo.interviews;

import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.*;

public class ParkingLotTest {

    @Test
    public void testAddVehicle_WhenParkingLotIsFull() {
        Test1.ParkingLot parkingLot = new Test1.ParkingLot();

        // Simulate parking 80 vehicles
        for (int i = 0; i < 80; i++) {
            Test1.Vehicle vehicle = new Test1.Vehicle(LocalDateTime.now(), "Car", 2.0);
            parkingLot.addVehicle(vehicle);
        }

        // Now trying to add the 81st vehicle
        Test1.Vehicle newVehicle = new Test1.Vehicle(LocalDateTime.now(), "Car", 2.0);
        assertFalse(parkingLot.addVehicle(newVehicle), "Should not allow more than 80 vehicles.");
    }

    @Test
    public void testAddVehicle_WhenSpaceIsAvailable() {
        Test1.ParkingLot parkingLot = new Test1.ParkingLot();

        // Simulate parking 79 vehicles
        for (int i = 0; i < 79; i++) {
            Test1.Vehicle vehicle = new Test1.Vehicle(LocalDateTime.now(), "Car", 2.0);
            parkingLot.addVehicle(vehicle);
        }

        // Now trying to add the 80th vehicle
        Test1.Vehicle newVehicle = new Test1.Vehicle(LocalDateTime.now(), "Car", 2.0);
        assertTrue(parkingLot.addVehicle(newVehicle), "Should allow up to 80 vehicles.");
    }

    @Test
    public void testDetermineParkingSpace_A() {
        Test1.ParkingLot parkingLot = new Test1.ParkingLot();
        Test1.Vehicle vehicle = new Test1.Vehicle(LocalDateTime.now(), "Car", 2.0);
        assertEquals("Parking Lot Space A", parkingLot.determineParkingSpace(vehicle));
    }

    @Test
    public void testDetermineParkingSpace_B() {
        Test1.ParkingLot parkingLot = new Test1.ParkingLot();
        Test1.Vehicle vehicle = new Test1.Vehicle(LocalDateTime.now(), "Bike", 1.5);
        assertEquals("Parking Lot Space B", parkingLot.determineParkingSpace(vehicle));
    }

    @Test
    public void testDetermineParkingSpace_NoSpace() {
        Test1.ParkingLot parkingLot = new Test1.ParkingLot();
        Test1.Vehicle vehicle = new Test1.Vehicle(LocalDateTime.now(), "Compact Car", 0.5);
        assertEquals("No suitable parking space available.", parkingLot.determineParkingSpace(vehicle));
    }
}
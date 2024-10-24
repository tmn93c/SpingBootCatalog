package com.example.demo.interviews;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class Test1 {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Vehicle {
        private LocalDateTime startTime;
        private String vehicleType;
        private double minimumWidth; // Minimum width in meters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ParkingTracking {
        private String parkingSpace;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private String vehicleType;
    }


    public static class ParkingLot {
        private Map<String, Double> priceMap;
        private int parkedVehicleCount;
        private Map<String, ParkingTracking> parkingHistoryMap;
        private Map<String, Vehicle> parkedVehicles;

        // Constructor
        public ParkingLot() {
            priceMap = new HashMap<>();
            priceMap.put("Car", 10.0);
            priceMap.put("Bike", 2.0);
            this.parkedVehicleCount = 0; // Initialize the count of parked vehicles
            this.parkingHistoryMap = new HashMap<>();
            this.parkedVehicles = new HashMap<>();
        }

        public void updateVehicleTypePrice(String vehicleType, double newPrice) {
            priceMap.put(vehicleType, newPrice);
            System.out.println("Updated price for vehicle type: " + vehicleType + " to: " + newPrice);
        }


        @Transactional
        public boolean addVehicle(Vehicle vehicle) {
            if (parkedVehicleCount >= 80) {
                System.out.println("Parking lot is full. Cannot add new vehicle.");
                return false; // Reject the new vehicle
            }
            parkedVehicleCount++; // Increment the count of parked vehicles
            // Use vehicle type + timeIn as a key (or another unique identifier)
            String key = vehicle.getVehicleType() + "-" + vehicle.getStartTime().toString();
            // Determine parking space
            String parkingSpace = determineParkingSpace(vehicle);
            ParkingTracking pacParkingTracking = new ParkingTracking(parkingSpace, vehicle.getStartTime(), null, vehicle.getVehicleType());
            parkingHistoryMap.put(key, pacParkingTracking); // Store parking tracking info
            parkedVehicles.put(key, vehicle); // Store the vehicle in the HashMap

            System.out.println("Vehicle parked in: " + parkingSpace);
            return true; // Successfully added the vehicle
        }

        public String determineParkingSpace(Vehicle vehicle) {
            double width = vehicle.getMinimumWidth();
            if (width >= 2.0) {
                return "Parking Lot Space A";
            } else if (width > 0.5) {
                return "Parking Lot Space B";
            } else {
                return "No suitable parking space available.";
            }
        }

        public double calculateParkingFee(ParkingTracking parkingTracking) {
            LocalDateTime timeOut = LocalDateTime.now();
            LocalDateTime timeIn = parkingTracking.getStartTime();


            // Calculate the duration between timeIn and timeOut
            long totalDays = Duration.between(timeIn, timeOut).toDays();


            // Round up to the nearest higher day if totalDays < 1
            totalDays = (totalDays < 1) ? 1 : (totalDays + 1);


            // Get the parking price based on vehicle type
            double pricePerDay = priceMap.getOrDefault(parkingTracking.getVehicleType(), 0.0);


            // Calculate total parking fee
            return pricePerDay * totalDays;
        }

        public void removeVehicle(String key) {
            Vehicle removedVehicle = parkedVehicles.remove(key);
            if (removedVehicle != null) {
                parkedVehicleCount--; // Decrement the count of parked vehicles
                ParkingTracking parkingTracking = parkingHistoryMap.remove(key); // Remove parking info

                if (parkingTracking != null) {
                    parkingTracking.setEndTime(LocalDateTime.now()); // Set time out when removing
                    double totalFee = calculateParkingFee(parkingTracking);
                    System.out.println("Vehicle removed: " + key + " | Time out: " + parkingTracking.getEndTime());
                    System.out.println("Total parking fee: $" + totalFee);
                }
            } else {
                System.out.println("No vehicle found with key: " + key);
            }
        }
    }


    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        Vehicle bike = new Vehicle(LocalDateTime.now().minusHours(24), "Bike", 1.0);
        parkingLot.addVehicle(bike);

        Vehicle car = new Vehicle(LocalDateTime.now().minusHours(24),"Car", 2.0);
        parkingLot.addVehicle(car);

        // When a vehicle leaves, it must be notified of its total parking fee.
        String key = "Car-" + car.getStartTime().toString();
        parkingLot.removeVehicle(key);

        // Test updating vehicle type price
        parkingLot.updateVehicleTypePrice("Van", 18.0);
        Vehicle van = new Vehicle(LocalDateTime.now().minusHours(24), "Van", 1.8); // Assuming a Van class exists
        parkingLot.addVehicle(van);
    }

}



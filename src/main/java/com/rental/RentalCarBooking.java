package com.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalCarBooking {

    @Autowired
    private Car car;

    public String bookCar(String customerName) {
        if (car.isBooked()) {
            throw new IllegalStateException("Car " + car.getModel() + " is already booked!");
        }
        car.setBooked(true);
        String message = "Car " + car.getModel() + " has been booked by " + customerName + ".";
        System.out.println(message);
        return message;
    }

    public String releaseCar(String customerName) {
        if (!car.isBooked()) {
            throw new IllegalStateException("Car " + car.getModel() + " is not currently booked!");
        }
        car.setBooked(false);
        String message = "Car " + car.getModel() + " has been released by " + customerName + ".";
        System.out.println(message);
        return message;
    }

    public String viewStatus() {
        String status = car.isBooked() ? "Booked" : "Available";
        String message = "Car: " + car.getModel() + " | Status: " + status;
        System.out.println(message);
        return message;
    }
}

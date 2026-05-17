package com.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentalCarBooking {

    @Autowired
    private List<Car> cars;

    public String bookCar(String customerName) {
        for (Car car : cars) {
            if (!car.isBooked()) {
                car.setBooked(true);
                String message = "Car " + car.getModel() + " has been booked by " + customerName + ".";
                System.out.println(message);
                return message;
            }
        }
        throw new IllegalStateException("No cars available for booking!");
    }

    public String releaseCar(String customerName) {
        for (Car car : cars) {
            if (car.isBooked()) {
                car.setBooked(false);
                String message = "Car " + car.getModel() + " has been released by " + customerName + ".";
                System.out.println(message);
                return message;
            }
        }
        throw new IllegalStateException("No cars are currently booked!");
    }

    public String viewStatus() {
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            String status = car.isBooked() ? "Booked" : "Available";
            String line = "Car: " + car.getModel() + " | Status: " + status;
            System.out.println(line);
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}

package com.rental;

import org.springframework.stereotype.Component;

@Component
public class Car {

    private String model;
    private boolean booked;

    public Car() {
        this.model = "Toyota Camry";
        this.booked = false;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "Car{model='" + model + "', booked=" + booked + "}";
    }
}

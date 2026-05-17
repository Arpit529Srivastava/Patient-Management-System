package com.rental;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        RentalCarBooking booking = context.getBean(RentalCarBooking.class);

        // 1. View initial status
        System.out.println("===== Viewing Initial Status =====");
        booking.viewStatus();

        // 2. Book the car
        System.out.println("\n===== Booking the Car =====");
        booking.bookCar("Arpit");

        // 3. View status after booking
        System.out.println("\n===== Viewing Status After Booking =====");
        booking.viewStatus();

        // 4. Try to book again (should throw exception - @AfterThrowing)
        System.out.println("\n===== Attempting Double Booking =====");
        try {
            booking.bookCar("Rahul");
        } catch (IllegalStateException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        // 5. Release the car (@AfterReturning + @After)
        System.out.println("\n===== Releasing the Car =====");
        booking.releaseCar("Arpit");

        // 6. View status after release
        System.out.println("\n===== Viewing Status After Release =====");
        booking.viewStatus();

        // 7. Try to release again (should throw exception - @AfterThrowing + @After)
        System.out.println("\n===== Attempting Release When Not Booked =====");
        try {
            booking.releaseCar("Arpit");
        } catch (IllegalStateException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        context.close();
    }
}

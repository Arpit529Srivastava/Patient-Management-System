package com.rental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.rental")
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public Car car1() {
        Car car = new Car();
        car.setModel("Toyota Camry");
        return car;
    }

    @Bean
    public Car car2() {
        Car car = new Car();
        car.setModel("Honda Civic");
        return car;
    }

    @Bean
    public Car car3() {
        Car car = new Car();
        car.setModel("Hyundai Creta");
        return car;
    }
}

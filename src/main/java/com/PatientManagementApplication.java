package com;

import com.entity.Patient;
import com.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.entity")
@EnableJpaRepositories(basePackages = "com.repository")
public class PatientManagementApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(PatientManagementApplication.class, args);
    }

    @Override
    public void run(String... args) {
        patientRepository.save(new Patient(0, "John Doe", 35, "Male", "9876543210"));
        patientRepository.save(new Patient(0, "Jane Smith", 28, "Female", "9876543211"));
        patientRepository.save(new Patient(0, "Robert Brown", 45, "Male", "9876543212"));
    }
}

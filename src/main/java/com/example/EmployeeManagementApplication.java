package com.example;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EmployeeManagementApplication implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Add employees
        Employee emp1 = new Employee(0, "Alice Johnson", "Software Engineer", 75000.00);
        Employee emp2 = new Employee(0, "Bob Smith", "Project Manager", 90000.00);
        Employee emp3 = new Employee(0, "Charlie Brown", "QA Analyst", 60000.00);

        Employee saved1 = employeeService.addEmployee(emp1);
        Employee saved2 = employeeService.addEmployee(emp2);
        Employee saved3 = employeeService.addEmployee(emp3);

        System.out.println("Added: " + saved1);
        System.out.println("Added: " + saved2);
        System.out.println("Added: " + saved3);

        // Get single employee
        System.out.println("Fetched: " + employeeService.getEmployee(saved1.getEmpid()));

        // Get all sorted by name ASC
        List<Employee> all = employeeService.getAllEmployee();
        System.out.println("All Employees (sorted by name):");
        all.forEach(System.out::println);

        // Update salary
        employeeService.updateSalary(saved1.getEmpid(), 80000.00);
        System.out.println("After salary update: " + employeeService.getEmployee(saved1.getEmpid()));

        // Update designation (@Query approach)
        employeeService.updateDesignation(saved2.getEmpid(), "Senior Project Manager");
        System.out.println("After designation update: " + employeeService.getEmployee(saved2.getEmpid()));

        // Delete existing employee
        employeeService.deleteEmployee(saved3.getEmpid());

        // Delete non-existent employee (should print message)
        employeeService.deleteEmployee(999);

        // Final list
        System.out.println("Final Employee List:");
        employeeService.getAllEmployee().forEach(System.out::println);
    }
}

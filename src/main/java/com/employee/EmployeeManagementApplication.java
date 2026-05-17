package com.employee;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
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

        // Get a single employee
        Employee fetched = employeeService.getEmployee(saved1.getEmpid());
        System.out.println("Fetched Employee: " + fetched);

        // Get all employees sorted by name ascending
        List<Employee> allEmployees = employeeService.getAllEmployee();
        System.out.println("All Employees (sorted by name):");
        allEmployees.forEach(System.out::println);

        // Update salary
        employeeService.updateSalary(saved1.getEmpid(), 80000.00);
        System.out.println("Updated salary for empid " + saved1.getEmpid());
        System.out.println("After salary update: " + employeeService.getEmployee(saved1.getEmpid()));

        // Update designation
        employeeService.updateDesignation(saved2.getEmpid(), "Senior Project Manager");
        System.out.println("Updated designation for empid " + saved2.getEmpid());
        System.out.println("After designation update: " + employeeService.getEmployee(saved2.getEmpid()));

        // Delete an employee
        employeeService.deleteEmployee(saved3.getEmpid());
        System.out.println("Deleted employee with empid " + saved3.getEmpid());

        // Try deleting a non-existent employee
        employeeService.deleteEmployee(999);

        // Final list
        System.out.println("Final Employee List:");
        employeeService.getAllEmployee().forEach(System.out::println);
    }
}

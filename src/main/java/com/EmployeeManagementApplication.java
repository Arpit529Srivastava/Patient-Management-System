package com;

import com.entity.Employee;
import com.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = "com.entity")
@EnableJpaRepositories(basePackages = "com.repository")
public class EmployeeManagementApplication implements CommandLineRunner {

    @Autowired
    IEmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Employee emp1 = new Employee(0, "Alice Johnson", "Software Engineer", 75000.00);
        Employee emp2 = new Employee(0, "Bob Smith", "Project Manager", 90000.00);
        Employee emp3 = new Employee(0, "Charlie Brown", "QA Analyst", 60000.00);

        Employee saved1 = employeeService.addEmployee(emp1);
        Employee saved2 = employeeService.addEmployee(emp2);
        Employee saved3 = employeeService.addEmployee(emp3);

        System.out.println("Added: " + saved1);
        System.out.println("Added: " + saved2);
        System.out.println("Added: " + saved3);

        System.out.println("Fetched: " + employeeService.getEmployee(saved1.getEmpid()));

        List<Employee> all = employeeService.getAllEmployee();
        System.out.println("All Employees (sorted by name):");
        all.forEach(System.out::println);

        employeeService.updateSalary(saved1.getEmpid(), 80000.00);
        System.out.println("After salary update: " + employeeService.getEmployee(saved1.getEmpid()));

        employeeService.updateDesignation(saved2.getEmpid(), "Senior Project Manager");
        System.out.println("After designation update: " + employeeService.getEmployee(saved2.getEmpid()));

        employeeService.deleteEmployee(saved3.getEmpid());
        employeeService.deleteEmployee(999);

        System.out.println("Final List:");
        employeeService.getAllEmployee().forEach(System.out::println);
    }
}

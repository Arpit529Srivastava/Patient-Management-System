package com.service;

import com.entity.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee addEmployee(Employee employee);

    Employee getEmployee(int empid);

    List<Employee> getAllEmployee();

    void updateSalary(int empid, double salary);

    void updateDesignation(int empid, String empdesignation);

    void deleteEmployee(int empid);
}

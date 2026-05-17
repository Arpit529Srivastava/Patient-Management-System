package com.service;

import com.entity.Employee;
import com.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(int empid) {
        return employeeRepository.findById(empid).orElse(null);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAllByOrderByEmpnameAsc();
    }

    @Override
    @Transactional
    public void updateSalary(int empid, double salary) {
        employeeRepository.updateSalaryByEmpid(empid, salary);
    }

    @Override
    @Transactional
    public void updateDesignation(int empid, String empdesignation) {
        employeeRepository.updateDesignationByEmpid(empid, empdesignation);
    }

    @Override
    @Transactional
    public void deleteEmployee(int empid) {
        Optional<Employee> optional = employeeRepository.findById(empid);
        if (optional.isPresent()) {
            employeeRepository.deleteById(empid);
        } else {
            System.out.println("Employee with empid " + empid + " does not exist.");
        }
    }
}

package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empid")
    private int empid;

    @Column(name = "empname", nullable = false, length = 100)
    private String empname;

    @Column(name = "empdesignation", nullable = false, length = 100)
    private String empdesignation;

    @Column(name = "empsalary", nullable = false)
    private double empsalary;

    public Employee() {}

    public Employee(int empid, String empname, String empdesignation, double empsalary) {
        this.empid = empid;
        this.empname = empname;
        this.empdesignation = empdesignation;
        this.empsalary = empsalary;
    }

    public int getEmpid() { return empid; }
    public void setEmpid(int empid) { this.empid = empid; }

    public String getEmpname() { return empname; }
    public void setEmpname(String empname) { this.empname = empname; }

    public String getEmpdesignation() { return empdesignation; }
    public void setEmpdesignation(String empdesignation) { this.empdesignation = empdesignation; }

    public double getEmpsalary() { return empsalary; }
    public void setEmpsalary(double empsalary) { this.empsalary = empsalary; }

    @Override
    public String toString() {
        return "Employee{empid=" + empid + ", empname='" + empname + "', empdesignation='" + empdesignation + "', empsalary=" + empsalary + '}';
    }
}

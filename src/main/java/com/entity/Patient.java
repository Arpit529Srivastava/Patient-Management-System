package com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "patient_name", nullable = false, length = 100)
    private String patientName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "contact_number", nullable = false, length = 15)
    private String contactNumber;

    public Patient() {}

    public Patient(int patientId, String patientName, int age, String gender, String contactNumber) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
    }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    @Override
    public String toString() {
        return "Patient{patientId=" + patientId + ", patientName='" + patientName + "', age=" + age
                + ", gender='" + gender + "', contactNumber='" + contactNumber + "'}";
    }
}

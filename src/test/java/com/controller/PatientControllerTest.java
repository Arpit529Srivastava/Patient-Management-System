package com.controller;

import com.entity.Patient;
import com.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        patientRepository.deleteAll();
        patientRepository.save(new Patient(0, "John Doe", 35, "Male", "9876543210"));
    }

    @Test
    void getPatientById_found_returns200() throws Exception {
        int patientId = patientRepository.findAll().get(0).getPatientId();

        mockMvc.perform(get("/api/patients/" + patientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId").value(patientId))
                .andExpect(jsonPath("$.patientName").value("John Doe"))
                .andExpect(jsonPath("$.age").value(35));
    }

    @Test
    void getPatientById_notFound_returns404() throws Exception {
        mockMvc.perform(get("/api/patients/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Patient not found with id: 999"));
    }
}

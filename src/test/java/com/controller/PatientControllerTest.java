package com.controller;

import com.entity.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dto.AuthenticationRequest;
import com.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    private static final String VALID_USERNAME = "john";
    private static final String VALID_PASSWORD = "john@123";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private String authToken;

    @BeforeEach
    void setUp() throws Exception {
        patientRepository.deleteAll();
        patientRepository.save(new Patient(0, "John Doe", 35, "Male", "9876543210"));
        authToken = obtainToken();
    }

    @Test
    void getPatientById_found_returns200() throws Exception {
        int patientId = patientRepository.findAll().get(0).getPatientId();

        mockMvc.perform(get("/api/patients/" + patientId)
                        .header("Authorization", "Bearer " + authToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId").value(patientId))
                .andExpect(jsonPath("$.patientName").value("John Doe"))
                .andExpect(jsonPath("$.age").value(35));
    }

    @Test
    void getPatientById_notFound_returns404() throws Exception {
        mockMvc.perform(get("/api/patients/999")
                        .header("Authorization", "Bearer " + authToken))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Patient not found with id: 999"));
    }

    @Test
    void getPatientById_withoutToken_returns401() throws Exception {
        mockMvc.perform(get("/api/patients/1"))
                .andExpect(status().isUnauthorized());
    }

    private String obtainToken() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest(VALID_USERNAME, VALID_PASSWORD);

        MvcResult result = mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        return objectMapper.readTree(result.getResponse().getContentAsString()).get("token").asText();
    }
}

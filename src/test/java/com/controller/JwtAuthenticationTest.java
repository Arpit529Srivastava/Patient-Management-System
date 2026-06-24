package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dto.AuthenticationRequest;
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
class JwtAuthenticationTest {

    private static final String VALID_USERNAME = "john";
    private static final String VALID_PASSWORD = "john@123";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void authenticate_validCredentials_returnsToken() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest(VALID_USERNAME, VALID_PASSWORD);

        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void authenticate_invalidUsername_returns401() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("invalid", VALID_PASSWORD);

        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Invalid username"));
    }

    @Test
    void authenticate_invalidPassword_returns401() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest(VALID_USERNAME, "wrongpassword");

        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Invalid password"));
    }

    @Test
    void getStudent_withoutToken_returns401() throws Exception {
        mockMvc.perform(get("/student/getStudent"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getStudent_withValidToken_returns200() throws Exception {
        String token = obtainToken();

        mockMvc.perform(get("/student/getStudent")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Student"))
                .andExpect(jsonPath("$.course").value("Computer Science"));
    }

    @Test
    void authenticateEndpoint_isAccessibleWithoutAuth() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest(VALID_USERNAME, VALID_PASSWORD);

        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
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

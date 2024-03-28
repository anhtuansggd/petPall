package com.petpal.backend.controllers;

import net.minidev.json.JSONObject;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


// Integration Test for Login Flow
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void login_validCredentials_shouldReturnJwtToken() {
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("username", "test");
        userJsonObject.put("password", "asdfasdf");
        HttpEntity<String> request = new HttpEntity<>(userJsonObject.toString(), headers);

        // Act
        ResponseEntity<String> response = restTemplate.postForEntity("/api/auth/login", request, String.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
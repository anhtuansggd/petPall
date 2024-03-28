package com.petpal.backend.service;

import com.petpal.backend.domain.User;
import com.petpal.backend.repository.UserRepository;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// Unit Test for Service Layer
@RunWith(SpringRunner.class)
public class UserDetailsServiceImplTest {
//    @ClassRule
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    ).withDatabaseName("testdb")
            .withUsername("sa")
            .withPassword("");

    @BeforeAll
    static void beforeAll(){
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll(){
        postgreSQLContainer.stop();
    }


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void loadUserByUsername_existingUsername_shouldReturnUser() {
        // Arrange
        User user = new User();
        user.setUsername("test");
        when(userRepository.findByUsername("test")).thenReturn(Optional.of(user));

        // Act
        UserDetails result = userDetailsService.loadUserByUsername("test");

        // Assert
        assertEquals("test", result.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_nonExistingUsername_shouldThrowUsernameNotFoundException() {
        // Arrange
        when(userRepository.findByUsername("test")).thenReturn(Optional.empty());

        // Act
        userDetailsService.loadUserByUsername("test");
    }
}

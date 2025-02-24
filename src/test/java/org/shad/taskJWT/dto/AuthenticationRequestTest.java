package org.shad.taskJWT.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationRequestTest {

    private Validator validator;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Initialize Jakarta Validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        // Initialize ObjectMapper for JSON testing
        objectMapper = new ObjectMapper();
    }

    @Nested
    @DisplayName("Object Creation Tests")
    class ObjectCreationTests {
        @Test
        @DisplayName("Should create a valid AuthenticationRequest object")
        void shouldCreateValidAuthenticationRequest() {
            // Given
            String username = "testUser";
            String password = "securePassword";

            // When
            AuthenticationRequest request = new AuthenticationRequest(username, password);

            // Then
            assertNotNull(request);
            assertEquals(username, request.username());
            assertEquals(password, request.password());
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {
        @Test
        @DisplayName("Should pass validation when username and password are provided")
        void shouldPassValidationWhenFieldsAreValid() {
            // Given
            AuthenticationRequest request = new AuthenticationRequest("testUser", "securePassword");

            // When
            Set<ConstraintViolation<AuthenticationRequest>> violations = validator.validate(request);

            // Then
            assertTrue(violations.isEmpty(), "No validation errors should be present");
        }

        @Test
        @DisplayName("Should fail validation when username is blank")
        void shouldFailValidationWhenUsernameIsBlank() {
            // Given
            AuthenticationRequest request = new AuthenticationRequest("", "securePassword");

            // When
            Set<ConstraintViolation<AuthenticationRequest>> violations = validator.validate(request);

            // Then
            assertFalse(violations.isEmpty());
            assertThat(violations).anyMatch(v -> v.getMessage().equals("Username is required"));
        }

        @Test
        @DisplayName("Should fail validation when password is blank")
        void shouldFailValidationWhenPasswordIsBlank() {
            // Given
            AuthenticationRequest request = new AuthenticationRequest("testUser", "");

            // When
            Set<ConstraintViolation<AuthenticationRequest>> violations = validator.validate(request);

            // Then
            assertFalse(violations.isEmpty());
            assertThat(violations).anyMatch(v -> v.getMessage().equals("Password is required"));
        }
    }

    @Nested
    @DisplayName("Serialization Tests")
    class SerializationTests {
        @Test
        @DisplayName("Should correctly serialize to JSON")
        void shouldSerializeToJson() throws JsonProcessingException {
            // Given
            AuthenticationRequest request = new AuthenticationRequest("testUser", "securePassword");

            // When
            String json = objectMapper.writeValueAsString(request);

            // Then
            assertTrue(json.contains("\"username\":\"testUser\""));
            assertTrue(json.contains("\"password\":\"securePassword\""));
        }

        @Test
        @DisplayName("Should correctly deserialize from JSON")
        void shouldDeserializeFromJson() throws JsonProcessingException {
            // Given
            String json = "{\"username\":\"testUser\",\"password\":\"securePassword\"}";

            // When
            AuthenticationRequest request = objectMapper.readValue(json, AuthenticationRequest.class);

            // Then
            assertNotNull(request);
            assertEquals("testUser", request.username());
            assertEquals("securePassword", request.password());
        }
    }
}
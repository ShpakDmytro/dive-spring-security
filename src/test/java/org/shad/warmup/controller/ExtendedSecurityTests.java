package org.shad.warmup.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

class ExtendedSecurityTests extends BaseSecurityTest {

    @Nested
    @DisplayName("Authentication Tests")
    class AuthenticationTests {

        @Test
        @DisplayName("Should reject invalid credentials")
        void whenInvalidCredentials_thenUnauthorized() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/stats")
                            .with(httpBasic("invalid", "invalid"))
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.status").value("error"))
                    .andExpect(jsonPath("$.message", containsString("Unauthorized")));
        }

        @Test
        @DisplayName("Should handle malformed authentication header")
        void whenMalformedAuthHeader_thenUnauthorized() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/stats")
                            .header("Authorization", "malformed_header")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.status").value("error"))
                    .andExpect(jsonPath("$.message", containsString("Unauthorized")));
        }
    }

    @Nested
    @DisplayName("Error Handler Tests")
    class ErrorHandlerTests {

        @Test
        @WithMockUser(roles = "USER")
        @DisplayName("Should return proper error response for access denied")
        void whenAccessDenied_thenProperErrorResponse() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/stats"))
                    .andExpect(status().isForbidden())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.status").value("error"))
                    .andExpect(jsonPath("$.message").exists())
                    .andExpect(jsonPath("$.timestamp").exists());
        }
    }
}
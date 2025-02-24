package org.shad.warmup.controller;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * Tests for endpoint security configuration.
 * Verifies that endpoints are properly secured and accessible
 * only to authorized users.
 */
class EndpointSecurityTests extends BaseSecurityTest {

    @Nested
    @DisplayName("Public Endpoints")
    class PublicEndpointsTests {

        @Test
        @DisplayName("Health check should be accessible without authentication")
        void healthCheck_ShouldBePublic() throws Exception {
            mockMvc.perform(get("/api/v1/public/health"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value("success"))
                    .andExpect(jsonPath("$.data.status").value("UP"));
        }

        @Test
        @DisplayName("Public endpoint should return correct content type")
        void publicEndpoint_ShouldReturnJsonContent() throws Exception {
            mockMvc.perform(get("/api/v1/public/health"))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(header().string("Content-Type", containsString("application/json")));
        }
    }

    @Nested
    @DisplayName("Protected Endpoints")
    class ProtectedEndpointsTests {

        @Test
        @DisplayName("Admin endpoint should require authentication")
        void adminEndpoint_ShouldRequireAuth() throws Exception {
            mockMvc.perform(get("/api/v1/admin/stats"))
                    .andExpect(status().isUnauthorized())
                    .andExpect(jsonPath("$.status").value("error"))
                    .andExpect(jsonPath("$.message").exists());
        }

        @Test
        @WithMockUser(roles = "USER")
        @DisplayName("Admin endpoint should require ADMIN role")
        void adminEndpoint_ShouldRequireAdminRole() throws Exception {
            mockMvc.perform(get("/api/v1/admin/stats"))
                    .andExpect(status().isForbidden())
                    .andExpect(jsonPath("$.status").value("error"));
        }

        @Test
        @WithMockUser(roles = "ADMIN")
        @DisplayName("Admin endpoint should be accessible with ADMIN role")
        void adminEndpoint_ShouldAllowAdminAccess() throws Exception {
            mockMvc.perform(get("/api/v1/admin/stats"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value("success"));
        }
    }
}
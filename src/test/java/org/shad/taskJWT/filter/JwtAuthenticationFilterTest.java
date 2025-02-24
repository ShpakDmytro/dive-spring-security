package org.shad.taskJWT.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.shad.taskJWT.service.JwtService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private final String validToken = "valid.jwt.token";
    private final String username = "testuser";
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDetails = new User(username, "password", List.of());
        SecurityContextHolder.clearContext();
    }

    @Nested
    @DisplayName("Valid JWT Authentication Tests")
    class ValidJwtTests {
        @Test
        @DisplayName("Should authenticate user when JWT is valid")
        void shouldAuthenticateUserWhenJwtIsValid() throws ServletException, IOException {
            // Given
            when(request.getHeader("Authorization")).thenReturn("Bearer " + validToken);
            when(jwtService.extractUsername(validToken)).thenReturn(username);
            when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
            when(jwtService.isTokenValid(validToken, userDetails)).thenReturn(true);

            // When
            jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

            // Then
            assertNotNull(SecurityContextHolder.getContext().getAuthentication());
            assertEquals(username, SecurityContextHolder.getContext().getAuthentication().getName());
            verify(filterChain).doFilter(request, response);
        }
    }

    @Nested
    @DisplayName("Invalid JWT Tests")
    class InvalidJwtTests {
        @Test
        @DisplayName("Should not authenticate when JWT is expired")
        void shouldNotAuthenticateWhenJwtIsExpired() throws ServletException, IOException {
            // Given
            String expiredToken = "expired.jwt.token";
            when(request.getHeader("Authorization")).thenReturn("Bearer " + expiredToken);
            when(jwtService.extractUsername(expiredToken)).thenThrow(
                    new ExpiredJwtException(null, null, "Token expired"));

            // When
            jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

            // Then
            assertNull(SecurityContextHolder.getContext().getAuthentication());
            verify(filterChain).doFilter(request, response);
        }

        @Test
        @DisplayName("Should not authenticate when JWT is malformed")
        void shouldNotAuthenticateWhenJwtIsMalformed() throws ServletException, IOException {
            // Given
            String malformedToken = "malformed.jwt.token";
            when(request.getHeader("Authorization")).thenReturn("Bearer " + malformedToken);
            when(jwtService.extractUsername(malformedToken)).thenThrow(new MalformedJwtException("Malformed JWT"));

            // When
            jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

            // Then
            assertNull(SecurityContextHolder.getContext().getAuthentication());
            verify(filterChain).doFilter(request, response);
        }

        @Test
        @DisplayName("Should not authenticate when JWT signature is invalid")
        void shouldNotAuthenticateWhenJwtSignatureIsInvalid() throws ServletException, IOException {
            // Given
            when(request.getHeader("Authorization")).thenReturn("Bearer " + validToken);
            when(jwtService.extractUsername(validToken)).thenThrow(new SignatureException("Invalid JWT signature"));

            // When
            jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

            // Then
            assertNull(SecurityContextHolder.getContext().getAuthentication());
            verify(filterChain).doFilter(request, response);
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {
        @Test
        @DisplayName("Should pass through filter when Authorization header is missing")
        void shouldPassThroughFilterWhenNoAuthorizationHeader() throws ServletException, IOException {
            // Given
            when(request.getHeader("Authorization")).thenReturn(null);

            // When
            jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

            // Then
            assertNull(SecurityContextHolder.getContext().getAuthentication());
            verify(filterChain).doFilter(request, response);
        }

        @Test
        @DisplayName("Should pass through filter when Authorization header does not start with Bearer")
        void shouldPassThroughFilterWhenAuthorizationHeaderDoesNotStartWithBearer()
                throws ServletException, IOException {
            // Given
            when(request.getHeader("Authorization")).thenReturn("Basic some_token");

            // When
            jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

            // Then
            assertNull(SecurityContextHolder.getContext().getAuthentication());
            verify(filterChain).doFilter(request, response);
        }
    }
}
package org.shad.warmup.config;

import lombok.RequiredArgsConstructor;
import org.shad.warmup.common.ApiConstants;
import org.shad.warmup.handler.CustomAccessDeniedHandler;
import org.shad.warmup.handler.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                createUserDetails("user", passwordEncoder, "USER"),
                createUserDetails("admin", passwordEncoder, "ADMIN")
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    private UserDetails createUserDetails(String username, PasswordEncoder encoder, String role) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    private String generateSecurePassword(String username) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
}
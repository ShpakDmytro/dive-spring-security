package org.shad.taskJWT.controller;

import lombok.RequiredArgsConstructor;
import org.shad.taskJWT.config.JwtProperties;
import org.shad.taskJWT.dto.AuthenticationRequest;
import org.shad.taskJWT.dto.AuthenticationResponse;
import org.shad.taskJWT.service.JwtService;
import org.shad.warmup.common.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    // todo: Implement this fields;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(
            @RequestBody AuthenticationRequest request) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
}
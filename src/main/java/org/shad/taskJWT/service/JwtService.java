package org.shad.taskJWT.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shad.taskJWT.config.JwtProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;

    public String generateToken(UserDetails userDetails) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    public String extractUsername(String token) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    public Claims extractAllClaims(String token) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    private Key getSigningKey() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    private boolean isTokenExpired(String token) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    public Object extractAuthorities(String token) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

}
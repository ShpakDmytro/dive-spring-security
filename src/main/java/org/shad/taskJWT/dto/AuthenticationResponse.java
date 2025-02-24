package org.shad.taskJWT.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.shad.taskJWT.config.JwtProperties;

import java.time.LocalDateTime;

public record AuthenticationResponse(
        // todo: Implement this fields
) {

    public static AuthenticationResponse fromToken(
            String token,
            String username,
            JwtProperties jwtProperties) {

        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
}
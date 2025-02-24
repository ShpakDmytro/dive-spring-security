package org.shad.warmup.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ApiResponse<T> {
    // todo: Implement this fields

    public static <T> ApiResponse<T> success(T data) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    public static <T> ApiResponse<T> error(String message) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
}
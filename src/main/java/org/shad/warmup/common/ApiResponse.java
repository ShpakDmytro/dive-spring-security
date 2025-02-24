package org.shad.warmup.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * Generic response wrapper for all API endpoints.
 * <p>
 * This class standardizes API responses by providing a consistent structure,
 * including status, message, response data, and a timestamp.
 * It supports both **successful** and **error** responses.
 * <p>
 * Example JSON response:
 * <pre>
 * {
 *     "status": "success",
 *     "message": null,
 *     "data": { ... },
 *     "timestamp": "2024-02-24T14:30:00.123"
 * }
 * </pre>
 *
 * @param <T> The type of data contained in the response.
 */
@Value
@Builder
public class ApiResponse<T> {
    String status;
    String message;
    T data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    LocalDateTime timestamp;

    /**
     * Creates a successful API response with the provided data.
     * <p>
     * @param <T>  The type of response data.
     * @param data The data payload to include in the response.
     * @return A success response containing the data.
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .status("success")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Creates an error API response with the provided message.
     * @param <T>     The type of response data (typically {@code Void} for errors).
     * @param message The error message describing the issue.
     * @return An error response with a message.
     */
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .status("error")
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
package org.shad.warmup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.shad.warmup.common.ApiConstants;
import org.shad.warmup.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Public endpoints controller
 */
@RestController
@RequestMapping(ApiConstants.API_BASE + ApiConstants.PUBLIC)
@Tag(name = "Public API", description = "Endpoints accessible without authentication")
public class PublicController {

    @GetMapping("/health")
    @Operation(summary = "Get API health status")
    public ResponseEntity<ApiResponse<Map<String, String>>> getHealthStatus() {
        Map<String, String> health = Map.of(
                "status", "UP",
                "version", "1.0.0"
        );
        return ResponseEntity.ok(ApiResponse.success(health));
    }
}

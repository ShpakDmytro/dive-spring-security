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
 * Admin endpoints controller
 */
@RestController
@RequestMapping(ApiConstants.API_BASE + ApiConstants.ADMIN)
@Tag(name = "Admin API", description = "Endpoints requiring ADMIN role")
public class AdminController {

    @GetMapping("/stats")
    @Operation(summary = "Get system statistics")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getStats() {
        Map<String, Object> stats = Map.of(
                "activeUsers", 100,
                "totalRequests", 1000,
                "cpuUsage", 45.5
        );
        return ResponseEntity.ok(ApiResponse.success(stats));
    }
}

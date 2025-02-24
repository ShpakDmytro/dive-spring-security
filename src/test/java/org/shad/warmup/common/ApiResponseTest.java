package org.shad.warmup.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
class ApiResponseTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenSerializingApiResponse_thenSuccessful() throws Exception {

        ApiResponse<String> response = ApiResponse.success("test data");

        String json = objectMapper.writeValueAsString(response);

        assertNotNull(json);
        assertTrue(json.contains("timestamp"));
        assertTrue(json.contains("test data"));
    }
}
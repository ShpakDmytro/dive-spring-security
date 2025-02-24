package org.shad.warmup.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
/**
 * Configuration class for customizing Jackson's {@link ObjectMapper}.
 * <p>
 * This configuration ensures that Jackson properly handles Java 8 date/time types,
 * such as {@link java.time.LocalDateTime}, by registering the {@link JavaTimeModule}.
 * <p>
 * **Why is this needed?**
 * <ul>
 *     <li>Spring Boot's default Jackson configuration does not support {@code java.time} types out of the box.</li>
 *     <li>Registers {@code JavaTimeModule} to enable proper serialization/deserialization of Java 8 time API.</li>
 *     <li>Defines a primary {@code ObjectMapper} bean to be used across the application.</li>
 * </ul>
 */
@Configuration
public class JacksonConfig {
    /**
     * Provides a customized {@link ObjectMapper} bean with support for Java 8 date/time API.
     * <p>
     * This method registers the {@link JavaTimeModule} to ensure proper serialization and deserialization
     * of {@code LocalDateTime}, {@code LocalDate}, {@code LocalTime}, and other Java 8 date/time types.
     *
     * @return A configured instance of {@link ObjectMapper}.
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
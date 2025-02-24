package org.shad.taskJWT.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(classes = JwtPropertiesTest.TestConfig.class)
@TestPropertySource(properties = {
        "jwt.secret=new-secret",
        "jwt.expiration-ms=7200000",
        "jwt.issuer=new-issuer"
})
class JwtPropertiesTest {
    @Autowired
    private JwtProperties jwtProperties;

    @Test
    @DisplayName("Should load JWT properties correctly from application properties")
    void shouldLoadJwtPropertiesCorrectly() {
        assertThat(jwtProperties).isNotNull();
        assertThat(jwtProperties.getSecret()).isEqualTo("new-secret");
        assertThat(jwtProperties.getExpirationMs()).isEqualTo(7200000L);
        assertThat(jwtProperties.getIssuer()).isEqualTo("new-issuer");
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public JwtProperties jwtProperties() {
            return new JwtProperties();
        }
    }
}
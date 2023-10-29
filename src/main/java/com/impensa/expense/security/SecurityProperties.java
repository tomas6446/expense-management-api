package com.impensa.expense.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tomas Kozakas
 */
@Configuration
@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProperties {
    private String secretKey;
}

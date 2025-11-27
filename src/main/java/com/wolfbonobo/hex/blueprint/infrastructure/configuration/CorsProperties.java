package com.wolfbonobo.hex.blueprint.infrastructure.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for global CORS settings.
 *
 * Values are loaded from application.yml / profile-specific yml files
 * under the "cors" prefix.
 */
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    /**
     * Allowed origins for cross-origin requests.
     * Example: http://localhost:3000, https://frontend.company.com
     */
    private List<String> allowedOrigins = new ArrayList<>();

    /**
     * Allowed HTTP methods for cross-origin requests.
     * Example: GET, POST, PUT, DELETE, OPTIONS
     */
    private List<String> allowedMethods = List.of("GET", "POST", "PUT", "DELETE", "OPTIONS");

    /**
     * Allowed HTTP headers.
     * Example: "*", "Authorization", "Content-Type"
     */
    private List<String> allowedHeaders = List.of("*");

    /**
     * Whether credentials such as cookies or auth headers are allowed.
     */
    private boolean allowCredentials = true;

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public List<String> getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(List<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public List<String> getAllowedHeaders() {
        return allowedHeaders;
    }

    public void setAllowedHeaders(List<String> allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    public boolean isAllowCredentials() {
        return allowCredentials;
    }

    public void setAllowCredentials(boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
    }
}

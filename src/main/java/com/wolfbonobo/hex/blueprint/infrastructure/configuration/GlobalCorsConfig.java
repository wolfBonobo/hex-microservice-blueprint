package com.wolfbonobo.hex.blueprint.infrastructure.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global CORS configuration for the entire application.
 * This configuration is applied to all incoming HTTP requests.
 *
 * Values are provided via CorsProperties and loaded from configuration files.
 */
@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class GlobalCorsConfig implements WebMvcConfigurer {

    private final CorsProperties corsProperties;

    public GlobalCorsConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(corsProperties.getAllowedOrigins().toArray(new String[0]))
                .allowedMethods(corsProperties.getAllowedMethods().toArray(new String[0]))
                .allowedHeaders(corsProperties.getAllowedHeaders().toArray(new String[0]))
                .allowCredentials(corsProperties.isAllowCredentials());
    }
}

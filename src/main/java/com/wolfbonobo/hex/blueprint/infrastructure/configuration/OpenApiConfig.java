package com.wolfbonobo.hex.blueprint.infrastructure.configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hexMicroserviceBlueprintOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hex Microservice Blueprint API")
                        .description("Template microservice using Hexagonal Architecture and CQRS")
                        .version("v1")
                        .contact(new Contact()
                                .name("Your Team")
                                .email("team@example.com")
                        )
                );
    }
}

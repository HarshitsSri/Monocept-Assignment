package com.swabhav.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
                .bearerFormat("Basic");

        return new OpenAPI()
                .info(new Info()
                        .title("Department Employee One-to-Many API")
                        .version("1.0")
                        .description("API for managing departments and employees using one-to-many mapping"))
                .components(new Components().addSecuritySchemes("basicAuth", securityScheme))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"));
    }
}

package com.openclassrooms.rental.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact().name("Axel").email("axelker@outlook.fr");
        Info information = new Info()
                .title("Rental Management System API")
                .version("1.0")
                .description("This API exposes endpoints to manage rentals.")
                .contact(contact);
        return new OpenAPI()
                .info(information)
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components().addSecuritySchemes("BearerAuth", new SecurityScheme()
                        .name("BearerAuth").type(SecurityScheme.Type.HTTP).scheme("bearer")
                        .bearerFormat("JWT")));

    }
}
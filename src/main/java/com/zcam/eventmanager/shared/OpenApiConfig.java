package com.zcam.eventmanager.shared;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Event Manager API",
                version = "v1",
                description = "API for event and place management with external address enrichment",
                contact = @Contact(
                        name = "Felipe Salmazo",
                        email = "felipesalmazo82@gmail.com"
                )
        )
)
public class OpenApiConfig {
}


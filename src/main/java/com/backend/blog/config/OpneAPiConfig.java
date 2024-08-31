package com.backend.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Zahid Ibn Rizwan",
            email = "zahidibnrizwan@gmail.com"
        ),
        description = "OpenApi documentation for spring Security",
        title = "OpenApi specifaction - Zahid",
        version = "1.0",
        license = @License(
            name = "license name",
            url = "www.google.com"
        ),
        termsOfService = "Terms of service"

    ),
    servers = {
        @Server(
            description = "Local ENV",
            url = "http://localhost:9090"
        )
    }
    
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpneAPiConfig {
    
}

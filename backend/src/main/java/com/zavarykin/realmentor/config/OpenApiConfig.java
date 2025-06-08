package com.zavarykin.realmentor.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "RealMentor API",
                version = "1.0",
                description = "Documentation for API"
        )
)
public class OpenApiConfig {
}

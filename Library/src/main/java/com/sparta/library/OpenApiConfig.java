package com.sparta.library;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// this class is to create a GUI for API documentation
@Configuration
public class OpenApiConfig {

    @Bean // the below bean will be constructed and added to the application context when app runs
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library API")
                        .version("1.0")
                        .description("API documentation for Farah's Library application"));
    }
}
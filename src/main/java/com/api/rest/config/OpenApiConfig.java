package com.api.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info().title("API RESTFull").version("v1").description("API RESTFull").termsOfService("").license(new License().name("Apache 2.0").url("")));
    }

}

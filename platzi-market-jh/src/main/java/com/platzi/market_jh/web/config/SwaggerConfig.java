package com.platzi.market_jh.web.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Platzi Market API")
                        .version("1.0")
                        .description("Documentación de la API para Platzi Market, realizado por Jhorman Salazar"));
    }
}

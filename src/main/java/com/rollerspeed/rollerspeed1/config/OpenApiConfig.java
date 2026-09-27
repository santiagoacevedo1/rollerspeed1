package com.rollerspeed.rollerspeed1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RollerSpeed Floristería API")
                        .version("1.0.0")
                        .description("API REST para gestión de floristería: tipos de flor, arreglos florales y pedidos")
                        .contact(new Contact()
                                .name("RollerSpeed")
                                .email("soporte@rollerspeed.com")))
                .servers(List.of(
                        new Server().url("http://localhost:8085").description("Servidor de desarrollo"),
                        new Server().url("https://api.rollerspeed.com").description("Servidor de producción")
                ));
    }
}
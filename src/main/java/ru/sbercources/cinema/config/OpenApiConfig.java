package ru.sbercources.cinema.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI libraryProject() {
        return new OpenAPI()
                .info(new Info()
                        .title("Онлайн кинотеатр")
                        .version("v1.0")
                        .contact(new Contact().name("dpoliwhi"))
                        .description("Сайт онлайн кинотеатра")
                );
    }
}


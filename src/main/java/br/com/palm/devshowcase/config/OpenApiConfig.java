package br.com.palm.devshowcase.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DevShowcase API")
                        .version("1.0.0")
                        .description("API REST para cadastro de perfis, projetos, tecnologias e feedbacks"));
    }
}

package org.fmartinez.api.note.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenConfig() {
        return new OpenAPI().info(new Info()
                .title("Block-Notes Microservice")
                .version("1.0.0")
                .description("API Rest demo application for managing users and their notes")
                .license(new License()
                        .name("Licence MIT")
                        .url("https://opensource.org/licenses/MIT"))
                .contact(new Contact()
                        .name("Facundo Martinez")
                        .email("martinez.facundo85@gmail.com")));
    }

    @Bean
    public GroupedOpenApi userAccountGroup() {
        String[] path = {"/api/user" + "/**"};
        return GroupedOpenApi.builder()
                .group("User-Account")
                .pathsToMatch(path)
                .build();
    }

    @Bean
    public GroupedOpenApi noteGroup() {
        String[] path = {"/api/mongo" + "/**"};
        return GroupedOpenApi.builder()
                .group("Notes")
                .pathsToMatch(path)
                .build();
    }

}

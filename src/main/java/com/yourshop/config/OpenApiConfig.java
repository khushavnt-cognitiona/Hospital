package com.yourshop.config;

import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("yourshop-public")
                .pathsToMatch("/api/**")
                .addOpenApiCustomizer(openApi -> openApi.setInfo(new Info().title("YourShop API").version("v1")))
                .build();
    }
}
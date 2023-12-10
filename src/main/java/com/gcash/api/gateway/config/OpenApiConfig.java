package com.gcash.api.gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Lugene Luistro (Google Cloud Platform + Cloud Firestore)")
                        .description("This API calculates the delivery cost of parcels, taking into account " +
                                "both weight and volume. Recognizing market fluctuations in pricing, " +
                                "it also includes a feature to manage and adjust costing rules accordingly.")
                        .version("v1"));
    }

}
package com.example.graphqlclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${graphql.endpoint}")
    private String graphqlEndpoint;

    @Bean
    public WebClient graphqlWebClient(){
        return WebClient.builder()
                .baseUrl(graphqlEndpoint)
                .defaultHeader("Content-Type","application/json")
                .build();
    }

}

package com.mudit.productservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
With the help of Configuration annotation, Spring will execute all methods in RestTempateConfig
 */

@Configuration
public class RestTemplateConfig {
    //Annotate a method that returns the obj
    //you want to put in application context

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

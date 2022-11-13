package com.sparta.sleepint.northwindapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class SwaggerConfig {

    @Autowired
    ObjectMapper objectMapper;

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {

        return new OpenAPI()
                .info(new Info()
                                .title("Northwind REST API")
                                .version(appVersion)
                                .description(appDesciption)
                                .version(appVersion)
                                .description(appDesciption)
                                .termsOfService("https://smartbear.com/terms-of-use/")
                                .license(new License().name("Apache 2.0").url("https://springdoc.org/")));
    }
}
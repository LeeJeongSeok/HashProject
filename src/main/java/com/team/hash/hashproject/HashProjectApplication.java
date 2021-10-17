package com.team.hash.hashproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class HashProjectApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location=" + "classpath:application.properties";

    public static void main(String[] args) {
        new SpringApplicationBuilder(HashProjectApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}

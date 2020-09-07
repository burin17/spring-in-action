package com.gmail.burinigor7.tacos.restclient;


import com.gmail.burinigor7.tacos.domain.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestExamples {
    public static void main(String[] args) {
        SpringApplication.run(RestExamples.class, args);
    }

    @Bean
    public Traverson traverson() {
        return new Traverson(URI.create("http://localhost:8080/api"),
                MediaTypes.HAL_JSON);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner getFLTOIngredient(TacoCloudClient client) {
        return args -> {
            log.info("--- GET ---");
            Ingredient FLTO = client.getIngredientById("FLTO");
            log.info(FLTO.toString());
        };
    }

    @Bean
    public CommandLineRunner getAllIngredient(TacoCloudClient client) {
        return args -> {
            log.info("--- GET ALL INGREDIENTS ---");
            Iterable<Ingredient> ingredients = client.getAllIngredients();
            log.info(ingredients.toString());
        };
    }
}

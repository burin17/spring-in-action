package com.gmail.burinigor7.tacos.restclient;

import com.gmail.burinigor7.tacos.domain.Ingredient;
import com.gmail.burinigor7.tacos.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TacoCloudClient {
    private RestTemplate restTemplate;
    private Traverson traverson;

    @Autowired
    public TacoCloudClient(RestTemplate restTemplate, Traverson traverson) {
        this.traverson = traverson;
        this.restTemplate = restTemplate;
    }

    public Ingredient getIngredientById(String ingredientId) {
        Map<String, String> urlParameters = new HashMap<>() {{
            put("id", ingredientId);
        }};
        URI requestForIngredient = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/api/ingredients/{id}")
                .build(urlParameters);
        ResponseEntity<Ingredient> responseEntity =
                restTemplate.getForEntity(requestForIngredient, Ingredient.class);
        log.info("Fetching date: " + responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/api/ingredients/{id}",
                ingredient,
                ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        restTemplate.delete("http://localhost:8080/api/ingredients/{id}",
                ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity = restTemplate
                .postForEntity("http://localhost:8080/api/ingredients",
                        ingredient, Ingredient.class);
        log.info("Newly created resource location: " +
                responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }

    public Iterable<Ingredient> getAllIngredients() {
        ParameterizedTypeReference<CollectionModel<Ingredient>> ingredientsType =
                new ParameterizedTypeReference<>() {};
        CollectionModel<Ingredient> ingredients =
                traverson.follow("ingredients")
                .toObject(ingredientsType);
        return ingredients.getContent();
    }
}
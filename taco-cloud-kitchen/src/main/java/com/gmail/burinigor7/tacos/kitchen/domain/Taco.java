package com.gmail.burinigor7.tacos.kitchen.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private String name;

    private Date createdAt;

    private List<Ingredient> ingredients;

}

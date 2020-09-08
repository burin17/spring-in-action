package com.gmail.burinigor7.tacos.kitchen.domain;

import lombok.Data;

@Data
public class Ingredient {

    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}

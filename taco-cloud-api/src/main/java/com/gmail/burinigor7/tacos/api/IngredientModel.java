package com.gmail.burinigor7.tacos.api;

import static com.gmail.burinigor7.tacos.domain.Ingredient.*;

import com.gmail.burinigor7.tacos.domain.Ingredient;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

public class IngredientModel extends RepresentationModel<IngredientModel> {
    @Getter
    private String name;
    @Getter
    private Type type;

    public IngredientModel(Ingredient ingredient) {
        name = ingredient.getName();
        type = ingredient.getType();
    }
}

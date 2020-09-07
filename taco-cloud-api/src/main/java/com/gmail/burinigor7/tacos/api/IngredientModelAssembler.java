package com.gmail.burinigor7.tacos.api;

import com.gmail.burinigor7.tacos.domain.Ingredient;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class IngredientModelAssembler
        extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {
    public IngredientModelAssembler() {
        super(IngredientController.class, IngredientModel.class);
    }

    @Override
    protected IngredientModel instantiateModel(Ingredient ingredient) {
        return new IngredientModel(ingredient);
    }

    @Override
    public IngredientModel toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }
}

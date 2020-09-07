package com.gmail.burinigor7.tacos.api;

import com.gmail.burinigor7.tacos.domain.Taco;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Relation(value = "taco", collectionRelation = "tacos")
public class TacoModel extends RepresentationModel<TacoModel> {
    private static final IngredientModelAssembler
            INGREDIENT_MODEL_ASSEMBLER = new IngredientModelAssembler();

    @Getter
    private final String name;
    @Getter
    private final Date createdAt;
    @Getter
    private final List<IngredientModel> ingredients = new ArrayList<>();

    public TacoModel(Taco taco) {
        name = taco.getName();
        createdAt = taco.getCreatedAt();
        taco.getIngredients()
            .forEach(ingredient ->
                    this.ingredients.add(
                            INGREDIENT_MODEL_ASSEMBLER.toModel(ingredient)));
    }
}

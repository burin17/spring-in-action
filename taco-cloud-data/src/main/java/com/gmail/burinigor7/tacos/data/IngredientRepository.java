package com.gmail.burinigor7.tacos.data;

import com.gmail.burinigor7.tacos.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")
public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {

}

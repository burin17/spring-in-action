package com.gmail.burinigor7.tacos.app;

import com.gmail.burinigor7.tacos.data.OrderRepository;
import com.gmail.burinigor7.tacos.domain.Ingredient;
import com.gmail.burinigor7.tacos.domain.Order;
import com.gmail.burinigor7.tacos.domain.Taco;
import com.gmail.burinigor7.tacos.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.gmail.burinigor7.tacos.domain.Ingredient.Type;
import com.gmail.burinigor7.tacos.data.IngredientRepository;
import com.gmail.burinigor7.tacos.data.TacoRepository;
import com.gmail.burinigor7.tacos.data.UserRepository;

import java.util.Arrays;
import java.util.List;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {


  @Bean
  public CommandLineRunner dataLoader(IngredientRepository ingredientRepository,
                                      UserRepository userRepository, PasswordEncoder encoder,
                                      TacoRepository tacoRepository, OrderRepository orderRepository) { // user repo for ease of testing with a built-in user
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
        Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
        Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
        Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
        Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
        Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
        Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
        Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
        Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
        Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
        ingredientRepository.save(flourTortilla);
        ingredientRepository.save(cornTortilla);
        ingredientRepository.save(groundBeef);
        ingredientRepository.save(carnitas);
        ingredientRepository.save(tomatoes);
        ingredientRepository.save(lettuce);
        ingredientRepository.save(cheddar);
        ingredientRepository.save(jack);
        ingredientRepository.save(salsa);
        ingredientRepository.save(sourCream);

        userRepository.save(new User("habuma", encoder.encode("pass"),
            "Craig Walls", "123 North Street", "Cross Roads", "TX", 
            "76227", "123-123-1234"));
        
        Taco taco1 = new Taco();
        taco1.setName("Carnivore");
        taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
        tacoRepository.save(taco1);

        Taco taco2 = new Taco();
        taco2.setName("Bovine Bounty");
        taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
        tacoRepository.save(taco2);

        Taco taco3 = new Taco();
        taco3.setName("Veg-Out");
        taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
        tacoRepository.save(taco3);
      }
    };
  }
  
}

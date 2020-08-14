package org.example.dishes.data.repository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.example.dishes.data.entity.Dish;
import org.example.dishes.data.entity.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DishRepositoryTest extends BasePersistTest {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void save() {
        Dish dish = new Dish();
        dish.setName("Блюдо №1");
        Recipe recipe = new Recipe();
        recipe.setText("Рецепт n1");
        recipe.setDish(dish);
        recipeRepository.save(recipe);
        dish.setRecipe(recipe);
        dishRepository.save(dish);

        assertNotNull(dish.getId());

        Dish dishPersist = dishRepository.findById(dish.getId()).orElse(null);
        assertNotNull(dishPersist);
        assertEquals(dish.getName(), dishPersist.getName());

        System.out.println(dishPersist);
    }

}
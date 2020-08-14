package org.example.dishes.data.repository;


import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.example.dishes.data.entity.Dish;
import org.example.dishes.data.entity.Recipe;
import org.example.dishes.data.entity.UserItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserItemRepositoryTest extends BasePersistTest {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserItemRepository userItemRepository;

    @Test
    public void save(){
        UserItem userItem = new UserItem();
        userItem.setUsername("testUsername");
        userItem.setPassword("testPassword");
        userItem.setLocalDate(LocalDate.now());

        Dish dish = new Dish();
        dish.setName("TestDish");

        Recipe recipe = new Recipe();
        recipe.setText("Recipe text");

        dish.setRecipe(recipe);
        userItem.setDishes(Collections.singletonList(dish));
        dish.setPrimaryUserItem(userItem);

        userItemRepository.save(userItem);
        assertNotNull(userItem.getId());
        UserItem testItem = userItemRepository.findById(userItem.getId()).orElse(null);
        assertNotNull(testItem);
        assertEquals(userItem.getUsername(), testItem.getUsername());

        System.out.println(testItem);
    }

}

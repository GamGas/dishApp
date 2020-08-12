package org.example.dishes.data.repository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.example.dishes.data.entity.Dish;
import org.example.dishes.data.entity.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void save() {
        Recipe recipe = new Recipe();
        recipe.setText("Рецепт №1");

        recipeRepository.save(recipe);

        assertNotNull(recipe.getId());

        Recipe recipePersist = recipeRepository.findById(recipe.getId()).orElse(null);
        assertNotNull(recipePersist);
        assertEquals(recipe.getText(), recipePersist.getText());
    }

}
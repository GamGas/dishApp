package org.example.dishdbtest.data.jpa.hibernate.entity.access.DAO;

import org.example.dishdbtest.data.jpa.hibernate.entity.Recipe;

import java.util.List;

public interface RecipeDAO {

    public Recipe findById(long id);

    public void save(Recipe recipe);

    public void update(Recipe recipe);

    public void delete(Recipe recipe);

    public List<Recipe> findAll();

}

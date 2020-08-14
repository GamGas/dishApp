package org.example.dishdbtest.data.jpa.hibernate.entity.access.DAO;

import org.example.dishdbtest.data.jpa.hibernate.entity.Dish;
import org.example.dishdbtest.data.jpa.hibernate.entity.UserItem;

import java.util.List;

public interface DishDAO {

    public Dish findById(long id);

    public void save(Dish dish);

    public void update(Dish dish);

    public void delete(Dish dish);

    public List<Dish> findAll();

}

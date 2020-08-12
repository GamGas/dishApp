package org.example.dishdbtest.data.jpa.hibernate.entity;

import org.example.dishdbtest.data.jpa.hibernate.entity.access.DAO.UserItemDAO;
import org.example.dishdbtest.data.jpa.hibernate.entity.access.DAOImpl.DishDAOImpl;
import org.example.dishdbtest.data.jpa.hibernate.entity.access.DAOImpl.UserItemDAOImpl;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelationTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() {

        UserItem u = new UserItem();
        u.setUsername("blablansky");
        u.setPassword("Xx6gusws");
        u.setLocalDate(LocalDate.now());

        Recipe r = new Recipe();
        r.setText("первый этап");

        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();

        p1.setTitle("Яйца");
        p2.setTitle("Соль");
        p3.setTitle("Масло подсолнечное");

        Dish d = new Dish();
        d.setName("Омлет");
        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);

        d.setDishProducts(products);

        d.setRecipe(r);

        u.setUserDishes(Collections.singletonList(d));
        DishDAOImpl.getInstance().save(d);
        UserItemDAOImpl.getInstance().save(u);
    }

    @Test
    public void testGreeter(){
        UserItemDAOImpl.getInstance()
                .findAll().forEach(System.out::println);
        DishDAOImpl.getInstance()
                .findAll().forEach(System.out::println);

        UserItem editItem = UserItemDAOImpl.getInstance().findById(1L);
        editItem.setUsername("sosiosel");
        UserItemDAOImpl.getInstance().update(editItem);

        System.out.println("Editing field username");
        UserItemDAOImpl.getInstance()
                .findAll().forEach(System.out::println);

    }
}

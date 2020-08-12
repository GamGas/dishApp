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
import java.util.Collection;
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

        Dish editItem = DishDAOImpl.getInstance().findById(2L);
        editItem.setName("Уха из петуха");
        DishDAOImpl.getInstance().update(editItem);

        System.out.println("Editing field name");
        DishDAOImpl.getInstance()
                .findAll().forEach(System.out::println);

    }

    @Test
    public void testDishFind(){
        UserItemDAOImpl userItemDAO = UserItemDAOImpl.getInstance();
        DishDAOImpl dishDAO = DishDAOImpl.getInstance();

        UserItem userItem = userItemDAO.findById(1L);
        Collection<Dish> dishes = userItem.getUserDishes();
        ArrayList<Dish> dishArrayList = new ArrayList<>(dishes);
        Dish dish = dishArrayList.get(0);
        System.out.printf("dish object id= %s, name= %s\n"
                , dish.getId(), dish.getName());
        dish.setName("Чурчхела");
        System.out.println("провели изменения полей");
        dishDAO.update(dish);
        dishDAO.findAll().forEach(System.out::println);
        userItemDAO.findAll().forEach(System.out::println);

        System.out.println("проверили изменения в БД");
    }
}

package org.example.dishes.data.entity;



import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Ignore
@ExtendWith(SpringExtension.class)
@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
public class RelationTest {
    private EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public void setUp() {

        entityManagerFactory = Persistence.createEntityManagerFactory("org.example.dishdbtest.data.jpa.hibernate");
        EntityManager em = entityManagerFactory.createEntityManager();
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
//        r.setDish(d);

        u.setUserDishes(Collections.singletonList(d));
        em.getTransaction().begin();
        em.merge(u);

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testGreeter(){
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.createQuery("from UserItem ", UserItem.class)
                .getResultList()
                .forEach(System.out::println);
        em.createQuery("from Dish ", Dish.class)
                .getResultList()
                .forEach(System.out::println);
        em.createQuery("from Product ", Product.class)
                .getResultList()
                .forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

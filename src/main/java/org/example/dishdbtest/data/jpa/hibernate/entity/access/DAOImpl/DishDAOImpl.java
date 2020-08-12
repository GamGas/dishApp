package org.example.dishdbtest.data.jpa.hibernate.entity.access.DAOImpl;

import org.example.dishdbtest.data.jpa.hibernate.entity.Dish;
import org.example.dishdbtest.data.jpa.hibernate.entity.access.DAO.DishDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DishDAOImpl implements DishDAO {

    private static DishDAOImpl INSTANCE;
    private EntityManagerFactory emf;
    private String persistenceUnitName = "org.example.dishdbtest.data.jpa.hibernate";
    private EntityManager em;

    public static DishDAOImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DishDAOImpl();
        }
        return INSTANCE;
    }

    private DishDAOImpl() {
        emf = Persistence.createEntityManagerFactory(
                persistenceUnitName);
    }

    @Override
    public Dish findById(long id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Dish result = em.find(Dish.class, id);
        em.close();
        return result;
    }

    @Override
    public void save(Dish dish) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(dish);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Dish dish) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(dish);
        em.close();


    }

    @Override
    public void delete(Dish dish) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(dish);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Dish> findAll() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        return em.createQuery("from Dish ", Dish.class)
                .getResultList();
    }
}

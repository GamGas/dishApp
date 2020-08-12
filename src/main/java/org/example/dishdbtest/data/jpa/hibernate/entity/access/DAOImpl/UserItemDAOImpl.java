package org.example.dishdbtest.data.jpa.hibernate.entity.access.DAOImpl;

import org.example.dishdbtest.data.jpa.hibernate.entity.Dish;
import org.example.dishdbtest.data.jpa.hibernate.entity.UserItem;
import org.example.dishdbtest.data.jpa.hibernate.entity.access.DAO.UserItemDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class UserItemDAOImpl implements UserItemDAO {

    private static UserItemDAOImpl INSTANCE;
    private EntityManagerFactory emf;
    private String persistenceUnitName = "org.example.dishdbtest.data.jpa.hibernate";
    private EntityManager em;

    public static UserItemDAOImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserItemDAOImpl();
        }
        return INSTANCE;
    }

    private UserItemDAOImpl() {
        emf = Persistence.createEntityManagerFactory(
                persistenceUnitName);
    }

    @Override
    public UserItem findById(long id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        UserItem result = em.find(UserItem.class, id);
        em.getTransaction().commit();
        em.close();
        return result;
    }

    @Override
    public void save(UserItem userItem) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(userItem);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(UserItem userItem) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(userItem);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void delete(UserItem userItem) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(userItem);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<UserItem> findAll() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        return em.createQuery("from UserItem ", UserItem.class)
                .getResultList();
    }

}

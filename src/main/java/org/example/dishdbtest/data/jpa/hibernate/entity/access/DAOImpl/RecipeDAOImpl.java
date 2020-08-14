package org.example.dishdbtest.data.jpa.hibernate.entity.access.DAOImpl;

import org.example.dishdbtest.data.jpa.hibernate.entity.Dish;
import org.example.dishdbtest.data.jpa.hibernate.entity.Recipe;
import org.example.dishdbtest.data.jpa.hibernate.entity.access.DAO.RecipeDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO {
    private static RecipeDAOImpl INSTANCE;
    private EntityManagerFactory emf;
    private String persistenceUnitName = "org.example.dishdbtest.data.jpa.hibernate";
    private EntityManager em;

    private RecipeDAOImpl(){
        emf = Persistence.createEntityManagerFactory(
                persistenceUnitName);
    }

    public static RecipeDAOImpl getInstance(){
        if(INSTANCE == null){
            INSTANCE = new RecipeDAOImpl();
        }
        return INSTANCE;
    }

    @Override
    public Recipe findById(long id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Recipe result = em.find(Recipe.class, id);
        em.close();
        return result;
    }

    @Override
    public void save(Recipe recipe) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(recipe);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Recipe recipe) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(recipe);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Recipe recipe) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(recipe);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Recipe> findAll() {

        em = emf.createEntityManager();
        em.getTransaction().begin();
        return em.createQuery("from Recipe ", Recipe.class)
                .getResultList();
    }
}

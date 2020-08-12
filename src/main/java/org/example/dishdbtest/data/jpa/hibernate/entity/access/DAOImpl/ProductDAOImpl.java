package org.example.dishdbtest.data.jpa.hibernate.entity.access.DAOImpl;

import org.example.dishdbtest.data.jpa.hibernate.entity.Product;
import org.example.dishdbtest.data.jpa.hibernate.entity.Product;
import org.example.dishdbtest.data.jpa.hibernate.entity.access.DAO.ProductDAO;
import org.example.dishdbtest.data.jpa.hibernate.entity.access.DAO.ProductDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static ProductDAOImpl INSTANCE;
    private EntityManagerFactory emf;
    private String persistenceUnitName = "org.example.dishdbtest.data.jpa.hibernate";
    private EntityManager em;

    private ProductDAOImpl(){
        emf = Persistence.createEntityManagerFactory(
                persistenceUnitName);
    }

    public static ProductDAOImpl getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ProductDAOImpl();
        }
        return INSTANCE;
    }

    @Override
    public Product findById(long id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Product result = em.find(Product.class, id);
        em.close();
        return result;
    }

    @Override
    public void save(Product product) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Product product) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Product product) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Product> findAll() {

        em = emf.createEntityManager();
        em.getTransaction().begin();
        return em.createQuery("from Product ", Product.class)
                .getResultList();
    }
}

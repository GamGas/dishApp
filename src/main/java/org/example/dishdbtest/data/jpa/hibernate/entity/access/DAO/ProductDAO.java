package org.example.dishdbtest.data.jpa.hibernate.entity.access.DAO;

import org.example.dishdbtest.data.jpa.hibernate.entity.Dish;
import org.example.dishdbtest.data.jpa.hibernate.entity.Product;

import java.util.List;

public interface ProductDAO {

    public Product findById(long id);

    public void save(Product product);

    public void update(Product product);

    public void delete(Product product);

    public List<Product> findAll();

}

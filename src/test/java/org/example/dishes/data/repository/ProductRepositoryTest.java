package org.example.dishes.data.repository;

import org.example.dishes.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ProductRepositoryTest extends BasePersistTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    DishRepository dishRepository;
    @Test
    public void save(){
        Product product = new Product();
        product.setTitle("Продукт №1");

        productRepository.save(product);

        assertNotNull(product.getId());

        Product productPersist = productRepository.findById(product.getId()).orElse(null);
        assertNotNull(productPersist);
        assertEquals(product.getTitle(), productPersist.getTitle());
    }
}

package org.example.dishes.data.repository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.example.dishes.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
@DataJpaTest
public class ProductRepositoryTest {
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

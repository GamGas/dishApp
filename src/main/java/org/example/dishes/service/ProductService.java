package org.example.dishes.service;

import lombok.RequiredArgsConstructor;
import org.example.dishes.data.dto.ProductList;
import org.example.dishes.data.entity.Dish;
import org.example.dishes.data.entity.Product;
import org.example.dishes.data.repository.ProductRepository;
import org.example.dishes.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final DishService dishService;

    public void create(String title) {
        Product product = new Product();
        product.setTitle(title);
        productRepository.save(product);
    }

    public ProductList findAll() {
        List<Product> products = productRepository.findAll();
        ProductList listRootMarker = new ProductList();
        if (products.isEmpty()) {
            throw new NotFoundException("Not dish in database!");
        }
        listRootMarker.setProductsList(products);
        return listRootMarker;
    }

    public Collection<Product> getByDish(Long dishId) {
        Dish dish = dishService.getById(dishId);
        if (CollectionUtils.isEmpty(dish.getDishProducts())) {
            throw new NotFoundException("Not dish in database!");
        }

        return dish.getDishProducts();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not product! id: " + id));
    }

    public void update(Long productId, String title) {
        Product product = getById(productId);
        product.setTitle(title);
        productRepository.save(product);
    }

    public void delete(Long productId) {
        Product product = getById(productId);
        productRepository.delete(product);
    }

    public void updateDish(long dish_id, String products) {
        String[] parsedProductsString = products.split(",");
        List<Product> productsList = new ArrayList<>();
        for (String productTitle : parsedProductsString) {
            long id = Long.parseLong(productTitle);
            if (productRepository.existsById(id)) {
                productsList.add(productRepository.findById(id).get());
            }
        }

        Dish dish = dishService.getById(dish_id);
        if (dish == null) {
            throw new NotFoundException("Not dish in database!");
        }

        dish.setDishProducts(productsList);
        dishService.save(dish);
    }

}

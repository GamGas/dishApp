package org.example.dishes.service;

import lombok.RequiredArgsConstructor;
import org.example.dishes.data.entity.Dish;
import org.example.dishes.data.entity.Product;
import org.example.dishes.data.entity.Recipe;
import org.example.dishes.data.entity.UserItem;
import org.example.dishes.data.repository.DishRepository;
import org.example.dishes.data.repository.ProductRepository;
import org.example.dishes.exception.NotFoundException;
import org.example.dishes.service.listmarker.DishListMarker;
import org.example.dishes.service.listmarker.ProductListMarker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    public ProductListMarker findAll() {
        List<Product> products = productRepository.findAll();
        ProductListMarker listRootMarker = new ProductListMarker();
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

    public void updateDish(long dish_id, String products){
        String[] parsedProductsString = products.split(",");
        List<Product> productsList = new ArrayList<>();
        for (String productTitle : parsedProductsString) {
            long id = Long.parseLong(productTitle);
            if(productRepository.existsById(id)){
                productsList.add(productRepository.findById(id).get());
            }
        }
        Dish dish = dishService.getById(dish_id);
        if(dish == null){
            throw new NotFoundException("Not dish in database!");
        }
            dish.setDishProducts(productsList);
            dishService.save(dish);
    }

}

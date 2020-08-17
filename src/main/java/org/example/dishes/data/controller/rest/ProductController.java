package org.example.dishes.data.controller.rest;

import org.example.dishes.data.entity.Dish;
import org.example.dishes.data.entity.Product;
import org.example.dishes.data.entity.UserItem;
import org.example.dishes.data.repository.DishRepository;
import org.example.dishes.data.repository.ProductRepository;
import org.example.dishes.data.repository.RecipeRepository;
import org.example.dishes.data.repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductRepository productRepository;
    private final DishRepository dishRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, DishRepository dishRepository) {
        this.productRepository = productRepository;
        this.dishRepository = dishRepository;
    }

    @PostMapping(value = "/products")
    public ResponseEntity<?> create(@RequestParam(name = "product_title") String product_title){
        Product product = new Product();
        product.setTitle(product_title);
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> read(){
        final List<Product> products = productRepository.findAll();
        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> read(@PathVariable(name="id")long id){
        final Optional<Product> product = productRepository.findById(id);
        return product.isPresent()
                ? new ResponseEntity<>(product.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id")long id,
                                    @RequestParam(name = "product_title") String product_title){
        if(!productRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        Product product = productRepository.findById(id).get();
        product.setTitle(product_title);
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<?> delete(@PathVariable(name="id")long id){
        if(!productRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/dishes/{id}/products")
    public ResponseEntity<?> updateDish(@PathVariable("id")long id,
                                    @RequestParam(name = "products") String products){
        List<Product> productsList = parseProductsList(products);
        Optional<Dish> dishOpt = dishRepository.findById(id);

        if(dishOpt.isPresent()){
            dishOpt.get().setDishProducts(productsList);
            dishRepository.save(dishOpt.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);


    }


    public List<Product> parseProductsList(String products){
        String[] parsedProductsString = products.split(",");
        List<Product> productsList = new ArrayList<>();
        for (String productTitle : parsedProductsString) {
            long id = Long.parseLong(productTitle);
            if(productRepository.existsById(id)){
                productsList.add(productRepository.findById(id).get());
            }
        }
        return productsList;
    }
}

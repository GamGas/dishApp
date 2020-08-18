package org.example.dishes.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.dishes.data.dto.ProductList;
import org.example.dishes.data.entity.Product;
import org.example.dishes.data.repository.DishRepository;
import org.example.dishes.data.repository.ProductRepository;
import org.example.dishes.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final DishRepository dishRepository;
    private final ProductService productService;

    @PostMapping(value = "/products")
    public ResponseEntity<?> create(@RequestParam(name = "product_title") String product_title){
        productService.create(product_title);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/products")
    public ProductList read(){
        return productService.findAll();
    }

    @GetMapping(value = "/products/{id}")
    public Product read(@PathVariable(name="id")long id){
        return productService.getById(id);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id")long id,
                                    @RequestParam(name = "product_title") String product_title){
        productService.update(id, product_title);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<?> delete(@PathVariable(name="id")long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/dishes/{id}/products")
    public ResponseEntity<?> updateDish(@PathVariable("id")long id,
                                    @RequestParam(name = "products") String products){

        productService.updateDish(id, products);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}

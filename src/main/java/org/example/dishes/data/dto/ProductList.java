package org.example.dishes.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.dishes.data.entity.Product;

import java.util.List;

public class ProductList {
    @Getter
    @Setter
    private List<Product> productsList;
}

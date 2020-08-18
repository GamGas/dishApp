package org.example.dishes.service.listmarker;

import lombok.Getter;
import lombok.Setter;
import org.example.dishes.data.entity.Dish;
import org.example.dishes.data.entity.Product;

import java.util.List;

public class ProductListMarker {
    @Getter
    @Setter
    List<Product> productsList;
}

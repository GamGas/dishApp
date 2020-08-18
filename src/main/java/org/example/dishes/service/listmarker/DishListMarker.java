package org.example.dishes.service.listmarker;

import lombok.Getter;
import lombok.Setter;
import org.example.dishes.data.entity.Dish;

import java.util.List;

public class DishListMarker {
    @Getter
    @Setter
    List<Dish> dishesList;
}

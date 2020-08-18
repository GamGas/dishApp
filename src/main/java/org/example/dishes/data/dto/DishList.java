package org.example.dishes.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.dishes.data.entity.Dish;

import java.util.List;

public class DishList {
    @Getter
    @Setter
    private List<Dish> dishesList;
}

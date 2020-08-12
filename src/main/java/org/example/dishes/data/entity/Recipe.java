package org.example.dishes.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@SuppressWarnings("PMD")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Recipe extends BaseEntity {

    private String text;

    @OneToOne(mappedBy = "recipe")
    private Dish dish;

}

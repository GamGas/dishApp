package org.example.dishes.data.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Dish extends BaseEntity {

    private String name;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "RECIPE_ID", nullable = false)
    private Recipe recipe;

    @ManyToMany(mappedBy = "userDishes")
    private Collection<UserItem> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DISH_PRODUCTS",
            joinColumns = @JoinColumn(name = "DISH_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private Collection<Product> dishProducts;

}

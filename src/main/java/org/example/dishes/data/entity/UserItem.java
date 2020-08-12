package org.example.dishes.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class UserItem extends BaseEntity {

    private String username;

    private String password;

    private LocalDate localDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_DISHES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISH_ID"))
    private Collection<Dish> userDishes;

}

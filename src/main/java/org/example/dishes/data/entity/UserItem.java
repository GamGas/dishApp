package org.example.dishes.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "primaryUserItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Dish> dishes;



}

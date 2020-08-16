package org.example.dishes.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;



@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

    private String title;

    @ManyToMany(mappedBy = "dishProducts")
    @JsonBackReference
    private Collection<Dish> dishes;


}

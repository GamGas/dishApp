package org.example.dishdbtest.data.jpa.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.Collection;


@SuppressWarnings("PMD")
@Entity
public class Recipe extends AbstractIdentifiableObject{

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    @OneToOne(mappedBy = "recipe")
    private Dish dish;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe{" +
                "recipe text='" + text + '\'' +
                "=[");
            sb.append(dish.getName() + ", ");
        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 2));

        sb.append("]}");
        return sb.toString();

    }
}

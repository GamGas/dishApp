package org.example.dishdbtest.data.jpa.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;


@SuppressWarnings("PMD")
@Entity
public class Product extends AbstractIdentifiableObject{

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "dishProducts")
    private Collection<Dish> dishes;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{" + "title='").append(title).append('\'').append("=[");
        for (Dish dish : dishes) {
            sb.append(dish.getName() + ", ");
        }
        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 2));

        sb.append("]}");
        return sb.toString();

    }
}

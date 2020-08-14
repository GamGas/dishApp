package org.example.dishes.data.entity;

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
    private Collection<Dish> dishes;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Продукт{" + "Название = '").append(title).append('\'').append(" Входит в =[");
        for (Dish dish : dishes) {
            sb.append(dish.getName() + ", ");
        }
        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 2));

        sb.append("]}");
        return sb.toString();

    }

}

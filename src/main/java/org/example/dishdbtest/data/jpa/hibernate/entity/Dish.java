package org.example.dishdbtest.data.jpa.hibernate.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@SuppressWarnings("PMD")
@Entity
public class Dish extends AbstractIdentifiableObject {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "RECIPE_ID", nullable = false)
    private Recipe recipe;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "userDishes")
    private Collection<UserItem> users;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DISH_PRODUCTS",
            joinColumns = @JoinColumn(name = "DISH_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private Collection<Product> dishProducts;


    public Dish() {
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("");
        sb.append("Dish{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                "=[");
        for (UserItem user : users) {
            sb.append(user.getUsername() + ", ");
        }
        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 2));

        sb.append("] products = [" );

        for (Product product : dishProducts) {
            sb.append(product.getTitle() + ", ");
        }

        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 2));

        sb.append("]"+"recipe = \'"+recipe+"\'}");
        return sb.toString();
    }
}

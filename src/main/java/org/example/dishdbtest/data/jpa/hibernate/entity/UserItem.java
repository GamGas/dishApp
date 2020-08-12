package org.example.dishdbtest.data.jpa.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class UserItem extends AbstractIdentifiableObject {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private LocalDate localDate;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_DISHES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISH_ID"))
    private Collection<Dish> userDishes;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("User{" +
                "id='" + getId() + '\'' +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", localDate=" + localDate +
                ", userDishes=[");
        for (Dish userDish : userDishes) {
            sb.append(userDish.getName() + ", ");
        }

        sb = new StringBuilder(sb.substring(0, sb.toString().length() - 2));
        sb.append("]}");
        return sb.toString();
    }

    public UserItem() {
    }
}

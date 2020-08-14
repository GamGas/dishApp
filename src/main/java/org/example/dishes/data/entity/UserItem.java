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

    @OneToMany(mappedBy = "primaryUserItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Dish> dishes;

    @Override
    public String toString() {
        return "UserItem{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", localDate=" + localDate +
                ", dishes=" + dishes +
                '}';
    }

}

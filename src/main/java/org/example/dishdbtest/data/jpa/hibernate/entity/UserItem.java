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

    public UserItem() {
    }
}

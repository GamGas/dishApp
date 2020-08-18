package org.example.dishes.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.dishes.data.entity.UserItem;

import java.util.Collection;

public class UserList {
    @Getter
    @Setter
    private Collection<UserItem> userItems;
}

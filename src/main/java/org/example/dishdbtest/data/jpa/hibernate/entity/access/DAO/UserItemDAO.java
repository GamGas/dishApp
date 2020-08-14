package org.example.dishdbtest.data.jpa.hibernate.entity.access.DAO;

import org.example.dishdbtest.data.jpa.hibernate.entity.UserItem;

import java.util.List;

public interface UserItemDAO {

    public UserItem findById(long id);

    public void save(UserItem userItem);

    public void update(UserItem userItem);

    public void delete(UserItem userItem);

    public List<UserItem> findAll();

}

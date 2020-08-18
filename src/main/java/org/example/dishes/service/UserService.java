package org.example.dishes.service;

import lombok.RequiredArgsConstructor;
import org.example.dishes.data.dto.UserList;
import org.example.dishes.data.entity.UserItem;
import org.example.dishes.data.repository.UserItemRepository;
import org.example.dishes.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserItemRepository repository;

    public void checkUserExist(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("User not found id: " + id );
        }
    }

    public void create(String username, String password) {
        UserItem userItem = new UserItem();
        userItem.setUsername(username);
        userItem.setPassword(password);
        userItem.setLocalDate(LocalDate.now());
        repository.save(userItem);
    }

    public void update(Long userId, String username, String password) {
        UserItem userItem = get(userId);
        userItem.setUsername(username);
        userItem.setPassword(password);
        userItem.setLocalDate(LocalDate.now());
        repository.save(userItem);
    }

    public void delete(Long id) {
        UserItem userItem = get(id);
        repository.delete(userItem);
    }

    public UserItem get(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new NotFoundException("User not found id: " + id ));
    }

    public UserList getAll() {
        UserList userList = new UserList();
        Collection<UserItem> userItems = repository.findAll();
        userList.setUserItems(userItems);
        return userList;
    }
}

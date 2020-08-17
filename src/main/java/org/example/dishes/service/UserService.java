package org.example.dishes.service;

import lombok.RequiredArgsConstructor;
import org.example.dishes.data.entity.UserItem;
import org.example.dishes.data.repository.UserItemRepository;
import org.example.dishes.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserItemRepository repository;

    public void checkUserExist(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("User not found id: " + id );
        }
    }

    public UserItem get(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new NotFoundException("User not found id: " + id ));
    }

}

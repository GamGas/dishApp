package org.example.dishes.data.controller.rest;

import org.example.dishes.data.entity.UserItem;
import org.example.dishes.data.repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class UserItemController {

    @Autowired
    private UserItemRepository userItemRepository;

    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestParam(name = "username") String username,
                                    @RequestParam(name = "password") String password){
        UserItem userItem = new UserItem();
        userItem.setUsername(username);
        userItem.setPassword(password);
        userItem.setLocalDate(LocalDate.now());
        userItemRepository.save(userItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserItem>> read(){
        final List<UserItem> users = userItemRepository.findAll();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserItem> read(@PathVariable(name="id")long id){
        final Optional<UserItem> user = userItemRepository.findById(id);
        return user.isPresent()
                ? new ResponseEntity<>(user.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id")long id,
                                    @RequestParam(name = "username") String username,
                                    @RequestParam(name = "password") String password){
        if(!userItemRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        UserItem userItem = userItemRepository.findById(id).get();
        userItem.setUsername(username);
        userItem.setPassword(password);
        userItem.setLocalDate(LocalDate.now());
        userItemRepository.save(userItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name="id")long id){
        if(!userItemRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        userItemRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

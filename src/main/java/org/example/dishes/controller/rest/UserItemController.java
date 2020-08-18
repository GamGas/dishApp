package org.example.dishes.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dishes.data.dto.UserList;
import org.example.dishes.data.entity.UserItem;
import org.example.dishes.data.repository.UserItemRepository;
import org.example.dishes.exception.NotFoundException;
import org.example.dishes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserItemController {

    private final UserService userService;

    @PostMapping(value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam(name = "username") String username,
                       @RequestParam(name = "password") String password) {
        userService.create(username, password);
    }

    @GetMapping(value = "/users")
    public UserList read() {
        return userService.getAll();
    }

    @GetMapping(value = "/users/{id}")
    public UserItem read(@PathVariable(name = "id") Long id) {
        return userService.get(id);
    }

    @PutMapping(value = "/users/{id}")
    public void update(@PathVariable(name = "id") long id,
                                    @RequestParam(name = "username") String username,
                                    @RequestParam(name = "password") String password) {
        userService.update(id, username, password);
    }

    @DeleteMapping(value = "/users/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleAllException(Exception ex) {
        log.error("Unexpected error", ex);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException(NotFoundException ex) {
        log.error("Not found entity", ex);
    }
}

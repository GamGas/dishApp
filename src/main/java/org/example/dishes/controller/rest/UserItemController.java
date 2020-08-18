package org.example.dishes.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dishes.data.entity.form.UserItemForm;
import org.example.dishes.data.dto.UserList;
import org.example.dishes.data.entity.UserItem;
import org.example.dishes.exception.NotFoundException;
import org.example.dishes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserItemController {

    private final UserService userService;

    @PostMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserItemForm useritemForm) {
        userService.create(useritemForm.getUsername(), useritemForm.getPassword());
    }

    @GetMapping(value = "/users")
    public UserList read() {
        return userService.getAll();
    }

    @GetMapping(value = "/users/{id}")
    public UserItem read(@PathVariable(name = "id") Long id) {
        return userService.get(id);
    }

    @PutMapping(value = "/users/{userid}",
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public void update(@PathVariable(name = "userid") long userid,
                       @RequestBody UserItemForm useritemForm) {

        userService.update(useritemForm.getId(),
                useritemForm.getUsername(),
                useritemForm.getPassword());
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

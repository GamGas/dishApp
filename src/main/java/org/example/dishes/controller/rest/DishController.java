package org.example.dishes.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.example.dishes.data.entity.Dish;
import org.example.dishes.data.entity.Recipe;
import org.example.dishes.data.repository.DishRepository;
import org.example.dishes.data.repository.UserItemRepository;
import org.example.dishes.exception.NotFoundException;
import org.example.dishes.service.DishService;
import org.example.dishes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping(value = "/users/{id}/dishes")
    public ResponseEntity<?> create(@PathVariable(name = "id") Long userId,
                                    @RequestParam(name = "name") String name,
                                    @RequestParam(name = "recipe") String recipe) {

        dishService.create(userId, name, recipe);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/dishes")
    public ResponseEntity<List<Dish>> read() {
        final List<Dish> dishes = dishService.findAll();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}/dishes")
    public ResponseEntity<Collection<Dish>> readDishes(@PathVariable(name = "id") Long id) {
        final Collection<Dish> dishes = dishService.getByUser(id);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping(value = "/dishes/{id}")
    public ResponseEntity<Dish> read(@PathVariable(name = "id") Long id) {
        final Dish dish = dishService.getById(id);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PutMapping(value = "/dishes/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @RequestParam(name = "name") String name,
                                    @RequestParam(name = "recipe") String recipe) {
        dishService.update(id, name, recipe);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = "/dishes/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        dishService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException(Exception ex) {

        log.error("Unexpected error", ex);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {

        log.error("Not found entity", ex);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}

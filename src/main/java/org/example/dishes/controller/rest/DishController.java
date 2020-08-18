package org.example.dishes.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dishes.data.entity.Dish;
import org.example.dishes.exception.NotFoundException;
import org.example.dishes.service.DishService;
import org.example.dishes.data.dto.DishList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping(value = "/users/{id}/dishes")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable(name = "id") Long userId,
                                    @RequestParam(name = "name") String name,
                                    @RequestParam(name = "recipe") String recipe) {

        dishService.create(userId, name, recipe);
    }

    @GetMapping(value = "/dishes")
    public DishList read() {
        return dishService.findAll();
    }

    @GetMapping(value = "/users/{id}/dishes")
    public Collection<Dish> readDishes(@PathVariable(name = "id") Long id) {
        return dishService.getByUser(id);
    }

    @GetMapping(value = "/dishes/{id}")
    public Dish read(@PathVariable(name = "id") Long id) {
        return dishService.getById(id);
    }

    @PutMapping(value = "/dishes/{id}")
    public void update(@PathVariable(name = "id") Long id,
                                    @RequestParam(name = "name") String name,
                                    @RequestParam(name = "recipe") String recipe) {
        dishService.update(id, name, recipe);
    }


    @DeleteMapping(value = "/dishes/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        dishService.delete(id);
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

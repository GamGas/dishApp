package org.example.dishes.data.controller.rest;

import org.example.dishes.data.entity.Dish;
import org.example.dishes.data.entity.Recipe;
import org.example.dishes.data.entity.UserItem;
import org.example.dishes.data.repository.DishRepository;
import org.example.dishes.data.repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class DishController {

    private final UserItemRepository userItemRepository;

    private final DishRepository dishRepository;

    @Autowired
    public DishController(UserItemRepository userItemRepository, DishRepository dishRepository) {
        this.userItemRepository = userItemRepository;
        this.dishRepository = dishRepository;
    }

    @PostMapping(value = "/users/{id}/dishes")
    public ResponseEntity<?> create(@PathVariable(name="id")long id,
                                    @RequestParam(name = "name") String name,
                                    @RequestParam(name = "recipe") String recipe){
        if(userItemRepository.existsById(id)){
            Dish dish = new Dish();
            dish.setName(name);
            dish.setPrimaryUserItem(userItemRepository.findById(id).get());
            Recipe mewRecipe = new Recipe();
            mewRecipe.setText(recipe);
            dish.setRecipe(mewRecipe);
            dishRepository.save(dish);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/dishes")
    public ResponseEntity<List<Dish>> read(){
        final List<Dish> dishes = dishRepository.findAll();
        return !dishes.isEmpty()
                ? new ResponseEntity<>(dishes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/users/{id}/dishes")
    public ResponseEntity<Collection<Dish>> readDishes(@PathVariable(name="id")long id){
        final Collection<Dish> dishes = userItemRepository.findById(id).get().getDishes();

        return dishes != null && !dishes.isEmpty()
                ? new ResponseEntity<>(dishes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/dishes/{id}")
    public ResponseEntity<Dish> read(@PathVariable(name="id")long id){
        final Optional<Dish> dish = dishRepository.findById(id);
        return dish.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/dishes/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id")long id,
                                    @RequestParam(name = "name") String name,
                                    @RequestParam(name = "recipe") String recipe){
        if(!dishRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        Dish dish = dishRepository.findById(id).get();
        dish.setName(name);
        dish.getRecipe().setText(recipe);
        dishRepository.save(dish);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/dishes/{id}")
    public ResponseEntity<?> delete(@PathVariable(name="id")long id){
        if(!dishRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        dishRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

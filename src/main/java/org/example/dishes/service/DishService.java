package org.example.dishes.service;

import lombok.RequiredArgsConstructor;
import org.example.dishes.data.entity.Dish;
import org.example.dishes.data.entity.Recipe;
import org.example.dishes.data.entity.UserItem;
import org.example.dishes.data.repository.DishRepository;
import org.example.dishes.exception.NotFoundException;
import org.example.dishes.data.dto.DishList;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final UserService userService;

    public void create(Long userId, String name, String recipe) {
        UserItem userItem = userService.get(userId);
        Dish dish = new Dish();
        dish.setName(name);
        dish.setPrimaryUserItem(userItem);
        Recipe mewRecipe = new Recipe();
        mewRecipe.setText(recipe);
        dish.setRecipe(mewRecipe);
        dishRepository.save(dish);
    }

    public DishList findAll() {
        List<Dish> dishes = dishRepository.findAll();
        DishList listRootMarker = new DishList();
        if (dishes.isEmpty()) {
            throw new NotFoundException("Not dish in database!");
        }
        listRootMarker.setDishesList(dishes);
        return listRootMarker;
    }

    public Collection<Dish> getByUser(Long userId) {
        UserItem userItem = userService.get(userId);
        if (CollectionUtils.isEmpty(userItem.getDishes())) {
            throw new NotFoundException("Not dish in database!");
        }

        return userItem.getDishes();
    }

    public Dish getById(Long id) {
        return dishRepository.findById(id)
                             .orElseThrow(() -> new NotFoundException("Not dish! id: " + id));
    }

    public void update(Long dishId, String name, String recipe) {
        Dish dish = getById(dishId);
        dish.setName(name);
        dish.getRecipe().setText(recipe);
        dishRepository.save(dish);
    }

    public void delete(Long dishId) {
        Dish dish = getById(dishId);
        dishRepository.delete(dish);
    }

    public void save(Dish dish){
        dishRepository.save(dish);
    }

}

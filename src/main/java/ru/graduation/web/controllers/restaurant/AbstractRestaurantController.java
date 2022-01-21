package ru.graduation.web.controllers.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import ru.graduation.model.Restaurant;
import ru.graduation.service.RestaurantService;

import java.util.List;

@Slf4j
public abstract class AbstractRestaurantController {

    @Autowired
    protected RestaurantService restaurantService;

    public Restaurant get(int id) {
        log.debug("Get restaurant with id : {}", id);
        return restaurantService.get(id);
    }

    public List<Restaurant> getAll() {
        log.debug("Get all restaurants");
        return restaurantService.getAll();
    }

    public List<Restaurant> getAllWithMenusAndDishes() {
        log.debug("Get all restaurants with menu and dishes");
        return restaurantService.getAllWithMenusAndDishes();
    }

    public Restaurant getAllWithMenusAndDishesById(int id) {
        log.debug("Get restaurants with menu and dishes with id: {}", id);
        return restaurantService.getAllWithMenusAndDishesById(id);
    }
}

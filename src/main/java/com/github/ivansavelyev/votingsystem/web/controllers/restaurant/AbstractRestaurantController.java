package com.github.ivansavelyev.votingsystem.web.controllers.restaurant;

import com.github.ivansavelyev.votingsystem.model.Restaurant;
import com.github.ivansavelyev.votingsystem.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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

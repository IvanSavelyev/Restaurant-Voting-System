package ru.graduation.web.controllers.restaurant;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}

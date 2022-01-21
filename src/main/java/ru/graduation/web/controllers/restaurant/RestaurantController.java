package ru.graduation.web.controllers.restaurant;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Restaurant;

import java.util.List;

import static ru.graduation.web.controllers.restaurant.RestaurantController.RESTAURANT_REST_URL;

@RestController
@RequestMapping(value = RESTAURANT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Restaurant Controller")

public class RestaurantController extends AbstractRestaurantController {

    public static final  String RESTAURANT_REST_URL = "api/rest/restaurants";

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping("full-info")
    public List<Restaurant> getFullInfo() {
        return super.getAllWithMenusAndDishes();
    }

    @GetMapping("/{id}/full-info")
    public Restaurant getFullInfoById(@PathVariable int id) {
        return super.getAllWithMenusAndDishesById(id);
    }

}

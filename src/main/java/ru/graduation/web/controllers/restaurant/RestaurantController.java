package ru.graduation.web.controllers.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Restaurant;

import java.util.List;

import static ru.graduation.web.controllers.restaurant.RestaurantController.RESTAURANT_REST_URL;

@RestController
@RequestMapping(value = RESTAURANT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantController extends AbstractRestaurantController {

    public final static String RESTAURANT_REST_URL = "api/rest/restaurants";

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getAll() {
        return super.getAll();
    }
}

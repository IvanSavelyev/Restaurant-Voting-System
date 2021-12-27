package ru.graduation.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.graduation.model.Restaurant;
import ru.graduation.service.RestaurantService;

import java.util.List;

import static ru.graduation.web.controllers.AdminRestaurantController.ADMIN_RESTAURANT_REST_URL;
import static ru.graduation.web.controllers.RestaurantController.RESTAURANT_REST_URL;

@RestController
@AllArgsConstructor
@RequestMapping(value = RESTAURANT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantController {

    public final static String RESTAURANT_REST_URL = "api/rest/restaurants";

    private RestaurantService restaurantService;

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        log.debug("Get restaurant with id : {}", id);
        return new ResponseEntity<>(restaurantService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.debug("Get all restaurants");
        return restaurantService.getAll();
    }
}

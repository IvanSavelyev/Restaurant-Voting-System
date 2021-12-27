package ru.graduation.web.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Restaurant;
import ru.graduation.service.RestaurantService;
import ru.graduation.util.ValidationUtil;

import java.util.List;

import static ru.graduation.web.controllers.AdminRestaurantController.ADMIN_RESTAURANT_REST_URL;

@RestController
@RequestMapping(value = ADMIN_RESTAURANT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminRestaurantController {

    public final static String ADMIN_RESTAURANT_REST_URL = "api/admin/rest/restaurants";

    private RestaurantService restaurantService;

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable Integer id) {
        log.debug("Get restaurant with id : {}", id);
        return new ResponseEntity<>(restaurantService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.debug("Get all restaurants");
        return restaurantService.getAll();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") int id) {
        log.debug("Delete restaurant with id : {}", id);
        restaurantService.delete(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant){
        ValidationUtil.checkNew(restaurant);
        log.info("create {}", restaurant);
        return new ResponseEntity<>(restaurantService.create(restaurant), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id){
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update {} with id={}", restaurant, id);
        restaurantService.update(restaurant);
    }
}

package ru.graduation.web.controllers.restaurant;


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

import javax.validation.Valid;
import java.util.List;

import static ru.graduation.web.controllers.restaurant.AdminRestaurantController.ADMIN_RESTAURANT_REST_URL;

@RestController
@RequestMapping(value = ADMIN_RESTAURANT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminRestaurantController extends AbstractRestaurantController {

    public final static String ADMIN_RESTAURANT_REST_URL = "api/admin/rest/restaurants";

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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.debug("Delete restaurant with id : {}", id);
        restaurantService.delete(id);
    }

    @PostMapping
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant){
        ValidationUtil.checkNew(restaurant);
        log.info("create {}", restaurant);
        return new ResponseEntity<>(restaurantService.create(restaurant), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id){
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update {} with id={}", restaurant, id);
        restaurantService.update(restaurant);
    }
}

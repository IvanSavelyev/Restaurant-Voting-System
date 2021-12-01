package ru.graduation.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Dish;
import ru.graduation.model.Restaurant;
import ru.graduation.service.DishService;

import static ru.graduation.web.controllers.AdminDishController.ADMIN_DISH_REST_URL;
import static ru.graduation.web.controllers.RestaurantController.RESTAURANT_REST_URL;

@RestController
@RequestMapping(value = ADMIN_DISH_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminDishController extends AbstractRestController{

    public final static String ADMIN_DISH_REST_URL = "api/rest/admin/dish";

    private final DishService dishService;

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getById(@PathVariable Integer id) {
        log.debug("Get dish with id : {}", id);
        return new ResponseEntity<>(dishService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  delete(@PathVariable Integer id, @PathVariable Integer menuId ) {
        log.debug("Delete dish with id: {} and menu id: {}", id, menuId);
        dishService.delete(id, menuId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> save(Dish dish ) {
        log.debug("Saving new dish");
        return new ResponseEntity<>(dishService.save(dish), HttpStatus.OK);
    }
}

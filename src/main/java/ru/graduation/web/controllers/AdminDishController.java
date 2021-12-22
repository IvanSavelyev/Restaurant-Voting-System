package ru.graduation.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Dish;
import ru.graduation.service.DishService;

import java.net.URI;
import java.util.List;

import static ru.graduation.web.controllers.AdminDishController.ADMIN_DISH_REST_URL;

@RestController
@RequestMapping(value = ADMIN_DISH_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminDishController {

    public final static String ADMIN_DISH_REST_URL = "api/rest/admin/dishes";

    private final DishService dishService;

    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable int id) {
        log.debug("Get dish with id : {}", id);
        return new ResponseEntity<>(dishService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public List<Dish> getByMenuId(@RequestParam int menuId) {
        log.debug("Get dish with menuId : {}", menuId);
        return dishService.getAllByMenuId(menuId);
    }

    @DeleteMapping("/{id}/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int menuId) {
        log.debug("Delete dish with id: {} and menu id: {}", id, menuId);
//        dishService.delete(id, menuId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  delete(@PathVariable int id) {
        log.debug("Delete dish with id: {}", id);
        dishService.delete(id);
    }

    @PostMapping(value = "/{menuId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable int menuId) {
        Dish created = dishService.create(dish, menuId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_DISH_REST_URL + "/{menuId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = "/{restaurantId}/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish, @PathVariable int menuId, @PathVariable int restaurantId){
        log.info("create {} for menu {} and restaurant {}", dish, menuId, restaurantId);
//        return new ResponseEntity<>(dishService.create(dish, menuId, restaurantId), HttpStatus.CREATED);
        return null;
    }
}

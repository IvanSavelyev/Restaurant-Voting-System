package ru.graduation.web.controllers.dish;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Dish;
import ru.graduation.service.DishService;
import ru.graduation.util.ValidationUtil;

import java.net.URI;
import java.util.List;

import static ru.graduation.web.controllers.dish.AdminDishController.ADMIN_DISH_REST_URL;

@RestController
@RequestMapping(value = ADMIN_DISH_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminDishController {

    public final static String ADMIN_DISH_REST_URL = "api/admin/rest/dishes";

    private final DishService dishService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Dish> get(@PathVariable int id) {
        log.debug("(Admin):Get dish with id : {}", id);
        return new ResponseEntity<>(dishService.get(id), HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Dish> getByMenuId(@RequestParam int menuId) {
        log.debug("(Admin):Get dishes by menuId : {}", menuId);
        return dishService.getAllByMenuId(menuId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @RequestParam int menuId) {
        log.debug("(Admin):Delete dish with id: {} in menuId: {}", id, menuId);
        dishService.delete(id, menuId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @RequestParam int menuId) {
        log.debug("(Admin):Update dish for menuId: {}", menuId);
        ValidationUtil.assureIdConsistent(dish, menuId);
        dishService.update(dish, menuId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @RequestParam int menuId) {
        log.debug("(Admin):Creating new dish for menuId {}", menuId);
        ValidationUtil.checkNew(dish);
        Dish created = dishService.create(dish, menuId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_DISH_REST_URL + "/{menuId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
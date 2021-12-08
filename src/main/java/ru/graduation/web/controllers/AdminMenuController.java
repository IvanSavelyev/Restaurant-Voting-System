package ru.graduation.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Dish;
import ru.graduation.model.Menu;
import ru.graduation.service.DishService;
import ru.graduation.service.MenuService;

import java.net.URI;

import static ru.graduation.web.controllers.AdminMenuController.ADMIN_MENU_REST_URL;

@RestController
@RequestMapping(value = ADMIN_MENU_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminMenuController {

    public final static String ADMIN_MENU_REST_URL = "api/rest/admin/menu";

    private final MenuService menuService;

    @GetMapping("/{id}")
    public ResponseEntity<Menu> get(@PathVariable int id) {
        log.debug("Get dish with id : {}", id);
        return new ResponseEntity<>(menuService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.debug("Delete dish with id: {} and menu id: {}", id, restaurantId);
        menuService.delete(id, restaurantId);
    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable Integer id) {
//        log.debug("Delete dish with id: {}", id);
//        menuService.delete(id);
//    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu, @PathVariable int restaurantId) {
        Menu created = menuService.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_MENU_REST_URL + "/{menuId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

//    @PostMapping(value = "/{restaurantId}/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Dish> create(@RequestBody Dish dish, @PathVariable int menuId, @PathVariable int restaurantId) {
//        log.info("create {} for menu {} and restaurant {}", dish, menuId, restaurantId);
//        return new ResponseEntity<>(menuService.create(dish, menuId, restaurantId), HttpStatus.CREATED);
//    }
}

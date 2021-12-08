package ru.graduation.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Menu;
import ru.graduation.service.MenuService;

import java.net.URI;
import java.util.List;

import static ru.graduation.web.controllers.AdminMenuController.ADMIN_MENU_REST_URL;

@RestController
@RequestMapping(value = ADMIN_MENU_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminMenuController {

    public final static String ADMIN_MENU_REST_URL = "api/rest/admin/menus";

    private final MenuService menuService;

    @GetMapping
    public List<Menu> getAll(@RequestParam int restaurantId) {
        log.debug("Get all menus for restaurant id : {}", restaurantId);
        return menuService.getAll(restaurantId);
    }

    @GetMapping(value = "/{id}")
    public Menu get(@PathVariable int id, @RequestParam int restaurantId) {
        log.debug("Get dish with id : {}", id);
        return menuService.get(id, restaurantId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @RequestParam int restaurantId) {
        log.debug("Delete dish with id: {} and menu id: {}", id, restaurantId);
        menuService.delete(id, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu, @RequestParam int restaurantId) {
        Menu created = menuService.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_MENU_REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}

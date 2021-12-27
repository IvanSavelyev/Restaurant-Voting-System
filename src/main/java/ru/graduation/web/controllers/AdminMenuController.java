package ru.graduation.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Dish;
import ru.graduation.model.Menu;
import ru.graduation.service.DishService;
import ru.graduation.service.MenuService;
import ru.graduation.util.exception.RequestException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.graduation.web.controllers.AdminMenuController.ADMIN_MENU_REST_URL;

@RestController
@RequestMapping(value = ADMIN_MENU_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminMenuController {

    public final static String ADMIN_MENU_REST_URL = "api/admin/rest/menus";

    private final MenuService menuService;

    //Worked
    @GetMapping
    public List<Menu> getAllMenusByRestaurantId(@RequestParam int restaurantId) {
        log.debug("Get menus with from restaurantId : {}", restaurantId);
        return menuService.getByRestaurantId(restaurantId);
    }

    @GetMapping("/{menuId}")
    public Menu getFullMenuByMenuIdAndRestaurantId(@PathVariable int menuId, @RequestParam int restaurantId) {
        log.debug("Get menus with from restaurantId : {}", restaurantId);
        return menuService.getWithDishByMenuIdAndRestaurantId(menuId, restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.debug("Delete menu with id: {}", id);
        menuService.delete(id);
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu, @PathVariable int restaurantId) {
        Menu created = menuService.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_MENU_REST_URL + "/{menuId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Menu menu, @PathVariable int restaurantId) {
        log.info("update menu with id={}", menu);
        menuService.update(menu, restaurantId);
    }
}

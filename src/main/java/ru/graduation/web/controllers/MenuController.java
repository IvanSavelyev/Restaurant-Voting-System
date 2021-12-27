package ru.graduation.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Menu;
import ru.graduation.service.MenuService;

import java.util.List;

import static ru.graduation.web.controllers.MenuController.MENU_REST_URL;
import static ru.graduation.web.controllers.RestaurantController.RESTAURANT_REST_URL;

@RestController
@AllArgsConstructor
@RequestMapping(value = MENU_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class MenuController {

    public final static String MENU_REST_URL = "api/rest/menus";

    private final MenuService menuService;

    @GetMapping
    public List<Menu> getAllMenusByRestaurantId(@RequestParam int restaurantId) {
        log.debug("Get menus with from restaurantId : {}", restaurantId);
        return menuService.getByRestaurantId(restaurantId);
    }

    @GetMapping("/{menuId}")
    public Menu getFullMenuByMenuIdAndRestaurantId(@PathVariable int menuId) {
        log.debug("Get full menu with  id : {}", menuId);
        return menuService.getWithDishByMenuId(menuId);
    }
}

package ru.graduation.web.controllers.menu;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Menu;

import java.util.List;

import static ru.graduation.web.controllers.menu.MenuController.MENU_REST_URL;

@RestController
@RequestMapping(value = MENU_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Menu Controller")
public class MenuController extends AbstractMenuController {

    public static final String MENU_REST_URL = "api/rest/menus";

    @GetMapping
    public Menu getAllMenusByRestaurantId(@RequestParam int restaurantId) {
        return super.getMenuByRestaurantId(restaurantId);
    }

    @GetMapping("/{menuId}")
    public Menu get(@PathVariable int menuId) {
        return super.getById(menuId);
    }

    @GetMapping("/{menuId}/with-dishes")
    public Menu getWithDishes(@PathVariable int menuId) {
        return super.getWithDishes(menuId);
    }
}

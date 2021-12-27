package ru.graduation.web.controllers.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Menu;
import ru.graduation.service.MenuService;

import java.util.List;

import static ru.graduation.web.controllers.menu.MenuController.MENU_REST_URL;

@RestController
@RequestMapping(value = MENU_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class MenuController extends AbstractMenuController {

    public final static String MENU_REST_URL = "api/rest/menus";

    @GetMapping
    public List<Menu> getAllMenusByRestaurantId(@RequestParam int restaurantId) {
        return super.getAllMenusByRestaurantId(restaurantId);
    }

    @GetMapping("/{menuId}")
    public Menu get(@PathVariable int menuId) {
        return super.getById(menuId);
    }

}

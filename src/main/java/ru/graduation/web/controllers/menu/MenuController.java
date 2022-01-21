package ru.graduation.web.controllers.menu;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Menu;
import ru.graduation.util.TimeUtil;

import java.time.LocalDate;
import java.util.List;

import static ru.graduation.web.controllers.menu.MenuController.MENU_REST_URL;

@RestController
@RequestMapping(value = MENU_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Menu Controller")
public class MenuController extends AbstractMenuController {

    public static final String MENU_REST_URL = "api/rest/menus";

    @GetMapping("/{id}")
    public Menu get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/{id}/with-dishes")
    public Menu getWithDishes(@PathVariable int id) {
        return super.getWithDishes(id);
    }

    @GetMapping
    public Menu getByRestaurantId(@RequestParam int restaurantId) {
        return super.getByRestaurantId(restaurantId);
    }

    @GetMapping("/with-dishes")
    public Menu getWithDishesByRestaurantIdAndDate(
            @RequestParam int restaurantId,
            @DateTimeFormat(pattern = TimeUtil.DATE_FORMAT_PATTERN)
            @RequestParam(value = "localDate",
                    defaultValue = "#{T(java.time.LocalDate).now().toString()}") LocalDate localDate) {
        return super.getWithDishesByRestaurantIdAndDate(localDate, restaurantId);
    }
}

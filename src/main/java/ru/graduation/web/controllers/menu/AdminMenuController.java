package ru.graduation.web.controllers.menu;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Menu;
import ru.graduation.to.MenuTo;
import ru.graduation.util.TimeUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

import static ru.graduation.web.controllers.menu.AdminMenuController.ADMIN_MENU_REST_URL;

@RestController
@RequestMapping(value = ADMIN_MENU_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Admin Menu Controller")
public class AdminMenuController extends AbstractMenuController {

    public static final String ADMIN_MENU_REST_URL = "api/admin/rest/menus";

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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.debug("Delete menu with id: {}", id);
        menuService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody MenuTo menuTo) {
        log.debug("Creating new menu from menuTo {}", menuTo);
        Menu created = menuService.create(menuTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_MENU_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody MenuTo menuTo, @PathVariable int id) {
        log.info("Update menu with id {} from menuTo {}", id, menuTo);
        menuService.update(menuTo, id);
    }
}

package ru.graduation.web.controllers.menu;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Menu;
import ru.graduation.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.graduation.web.controllers.menu.AdminMenuController.ADMIN_MENU_REST_URL;

@RestController
@RequestMapping(value = ADMIN_MENU_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Admin Menu Controller")
public class AdminMenuController extends AbstractMenuController {

    public final static String ADMIN_MENU_REST_URL = "api/admin/rest/menus";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Menu> getAllMenusByRestaurantId(@RequestParam int restaurantId) {
        return super.getAllMenusByRestaurantId(restaurantId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Menu get(@PathVariable int id) {
        return super.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.debug("(Admin):Delete menu with id: {}", id);
        menuService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody Menu menu, @RequestParam int restaurantId) {
        log.debug("(Admin):Creating new menu for restaurantId {}", restaurantId);
        ValidationUtil.checkNew(menu);
        Menu created = menuService.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_MENU_REST_URL + "/{restaurantId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Menu menu, @PathVariable int id, @RequestParam int restaurantId) {
        ValidationUtil.assureIdConsistent(menu, id);
        log.info("(Admin):Update menu with id {} and for restaurant {}", id, restaurantId);
        menuService.update(menu, restaurantId);
    }
}

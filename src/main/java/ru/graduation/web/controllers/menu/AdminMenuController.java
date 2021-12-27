package ru.graduation.web.controllers.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Menu;
import ru.graduation.service.MenuService;
import ru.graduation.util.ValidationUtil;

import java.net.URI;
import java.util.List;

import static ru.graduation.web.controllers.menu.AdminMenuController.ADMIN_MENU_REST_URL;

@RestController
@RequestMapping(value = ADMIN_MENU_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminMenuController extends AbstractMenuController {

    public final static String ADMIN_MENU_REST_URL = "api/admin/rest/menus";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Menu> getAllMenusByRestaurantId(@RequestParam int restaurantId) {
        return super.getAllMenusByRestaurantId(restaurantId);
    }

    @GetMapping("/{menuId}")
    @ResponseStatus(HttpStatus.OK)
    public Menu get(@PathVariable int menuId) {
        return super.getById(menuId);
    }

    @DeleteMapping("/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int menuId) {
        log.debug("(Admin):Delete menu with id: {}", menuId);
        menuService.delete(menuId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu, @RequestParam int restaurantId) {
        log.debug("(Admin):Creating new menu for restaurantId {}", restaurantId);
        ValidationUtil.checkNew(menu);
        Menu created = menuService.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_MENU_REST_URL + "/{restaurantId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Menu menu, @RequestParam int restaurantId) {
        ValidationUtil.assureIdConsistent(menu, restaurantId);
        log.info("(Admin):Update menu with id {}", restaurantId);
        menuService.update(menu, restaurantId);
    }
}
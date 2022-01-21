package ru.graduation.web.controllers.dish;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Dish;
import ru.graduation.service.DishService;
import ru.graduation.to.DishTo;
import ru.graduation.util.DishUtil;
import ru.graduation.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.graduation.web.controllers.dish.AdminDishController.ADMIN_DISH_REST_URL;

@RestController
@RequestMapping(value = ADMIN_DISH_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Tag(name = "Dish Controller")
public class AdminDishController {

    public static final String ADMIN_DISH_REST_URL = "api/admin/rest/dishes";

    private final DishService dishService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dish get(@PathVariable int id) {
        log.debug("Get dish with id : {}", id);
        return dishService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Dish> getByMenuId(@RequestParam int menuId) {
        log.debug("Get dishes by menuId : {}", menuId);
        return dishService.getAllByMenuId(menuId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        log.debug("Delete dish with id: {}", id);
        dishService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int id) {
        log.debug("Update dish from dishTo: {} with dish id: {}", dishTo, id);
        dishService.update(dishTo, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo dishTo) {
        log.debug("Creating new dish from dishTo {}", dishTo);
        Dish created = dishService.create(dishTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_DISH_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}

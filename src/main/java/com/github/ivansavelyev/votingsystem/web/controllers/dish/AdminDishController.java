package com.github.ivansavelyev.votingsystem.web.controllers.dish;

import com.github.ivansavelyev.votingsystem.model.Dish;
import com.github.ivansavelyev.votingsystem.service.DishService;
import com.github.ivansavelyev.votingsystem.to.DishTo;
import com.github.ivansavelyev.votingsystem.util.ValidationUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

import static com.github.ivansavelyev.votingsystem.web.controllers.dish.AdminDishController.ADMIN_DISH_REST_URL;

@RestController
@RequestMapping(value = ADMIN_DISH_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
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
    public Set<Dish> getByMenuId(@RequestParam int menuId) {
        log.debug("Get dishes by menuId : {}", menuId);
        return dishService.getAllByMenuId(menuId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.debug("Delete dish with id: {}", id);
        dishService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int id) {
        log.debug("Update dish from dishTo {} with dish id: {}", dishTo, id);
        ValidationUtil.assureIdConsistent(dishTo, id);
        dishService.update(dishTo, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo dishTo) {
        log.debug("Creating new dish from dishTo:  {}", dishTo);
        ValidationUtil.checkNew(dishTo);
        Dish created = dishService.create(dishTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_DISH_REST_URL + "/{menuId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}

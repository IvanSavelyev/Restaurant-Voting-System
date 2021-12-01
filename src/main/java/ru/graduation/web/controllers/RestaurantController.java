package ru.graduation.web.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Restaurant;
import ru.graduation.service.RestaurantService;

import javax.persistence.Access;

import static ru.graduation.web.controllers.RestaurantController.RESTAURANT_REST_URL;

@RestController
@RequestMapping(value = RESTAURANT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class RestaurantController extends AbstractRestController{

    public final static String RESTAURANT_REST_URL = "api/rest/restaurant";
    private RestaurantService service;

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable Integer id) {
        log.debug("Get restaurant with id : {}", id);
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
}

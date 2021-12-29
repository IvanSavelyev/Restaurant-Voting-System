package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Restaurant;
import ru.graduation.testdata.RestaurantTestData;
import ru.graduation.web.exeption.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.RestaurantTestData.*;

@SpringBootTest
@Transactional
public class RestaurantServiceTest {

    @Autowired
    protected RestaurantService restaurantService;

    @Test
    void delete() {
        restaurantService.delete(RESTAURANT_ID);
        assertThrows(NotFoundException.class, () -> restaurantService.get(RESTAURANT_ID));
    }

    @Test
    void deleteNotFoundById() {
        assertThrows(NotFoundException.class, () -> restaurantService.delete(NOT_FOUND_RESTAURANT));
    }

    @Test
    void get() {
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(RESTAURANT_ID), restaurant1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantService.get(NOT_FOUND_RESTAURANT));
    }

    @Test
    void getAll() {
        RESTAURANT_MATCHER.assertMatch(restaurantService.getAll(), restaurants);
    }

    @Test
    void update() {
        Restaurant updated = RestaurantTestData.getUpdated();
        restaurantService.update(updated);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(RESTAURANT_ID), RestaurantTestData.getUpdated());
    }

    @Test
    void create() {
        Restaurant created = restaurantService.create(RestaurantTestData.getNew());
        int newId = created.id();
        Restaurant newRestaurant = RestaurantTestData.getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }
}

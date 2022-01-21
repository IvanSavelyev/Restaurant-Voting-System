package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.TimingExtension;
import ru.graduation.model.Restaurant;
import ru.graduation.web.exeption.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.RestaurantTestData.*;

@SpringBootTest
@Transactional
@ExtendWith(TimingExtension.class)
@ActiveProfiles("test")
public class RestaurantServiceTest {

    @Autowired
    protected RestaurantService restaurantService;

    @Test
    void delete() {
        restaurantService.delete(RESTAURANT_ID);
        assertThrows(NotFoundException.class, () -> restaurantService.get(RESTAURANT_ID));
    }

    @Test
    void deleteNotFound() {
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
        Restaurant updated = getUpdated();
        restaurantService.update(updated, RESTAURANT_ID);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(RESTAURANT_ID), getUpdated());
    }

    @Test
    void create() {
        Restaurant created = restaurantService.create(getNew());
        int newId = created.id();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    void getAllWithMenusAndDishes() {
//        Restaurant restaurant = restaurantService.getAllWithMenusAndDishesById(RESTAURANT_ID);
//        RESTAURANT_MATCHER_WITH_MENU_AND_DISHES.assertMatch(restaurant, restaurant1);
    }

    @Test
    void getAllWithMenusAndDishesById() {
    }
}


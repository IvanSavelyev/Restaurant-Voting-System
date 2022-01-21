package com.github.ivansavelyev.votingsystem.service;

import com.github.ivansavelyev.votingsystem.TimingExtension;
import com.github.ivansavelyev.votingsystem.model.Restaurant;
import com.github.ivansavelyev.votingsystem.testdata.RestaurantTestData;
import com.github.ivansavelyev.votingsystem.web.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@ExtendWith(TimingExtension.class)
@ActiveProfiles("test")
public class RestaurantServiceTest {

    @Autowired
    protected RestaurantService restaurantService;

    @Test
    void delete() {
        restaurantService.delete(RestaurantTestData.RESTAURANT_ID);
        assertThrows(NotFoundException.class, () -> restaurantService.get(RestaurantTestData.RESTAURANT_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantService.delete(RestaurantTestData.NOT_FOUND_RESTAURANT));
    }

    @Test
    void get() {
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(restaurantService.get(RestaurantTestData.RESTAURANT_ID), RestaurantTestData.restaurant1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantService.get(RestaurantTestData.NOT_FOUND_RESTAURANT));
    }

    @Test
    void getAll() {
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(restaurantService.getAll(), RestaurantTestData.restaurants);
    }

    @Test
    void update() {
        Restaurant updated = RestaurantTestData.getUpdated();
        restaurantService.update(updated, RestaurantTestData.RESTAURANT_ID);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(restaurantService.get(RestaurantTestData.RESTAURANT_ID), RestaurantTestData.getUpdated());
    }

    @Test
    void create() {
        Restaurant created = restaurantService.create(RestaurantTestData.getNew());
        int newId = created.id();
        Restaurant newRestaurant = RestaurantTestData.getNew();
        newRestaurant.setId(newId);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }
}


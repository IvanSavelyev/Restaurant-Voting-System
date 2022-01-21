package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.TimingExtension;
import ru.graduation.model.Dish;
import ru.graduation.model.Menu;
import ru.graduation.testdata.MenuTestData;
import ru.graduation.to.DishTo;
import ru.graduation.to.MenuTo;
import ru.graduation.util.DishUtil;
import ru.graduation.util.MenuUtil;
import ru.graduation.web.exeption.NotFoundException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.DishTestData.*;
import static ru.graduation.testdata.DishTestData.DISH_MATCHER;
import static ru.graduation.testdata.MenuTestData.*;
import static ru.graduation.testdata.RestaurantTestData.RESTAURANT_ID;

@SpringBootTest
@Transactional
@ExtendWith(TimingExtension.class)
@ActiveProfiles("test")
public class MenuServiceTest {

    @Autowired
    protected MenuService menuService;

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> menuService.delete(NOT_FOUND_MENU));
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> menuService.get(NOT_FOUND_MENU));
    }

    @Test
    void get() {
        MENU_MATCHER.assertMatch(menuService.get(MENU1_ID), menu1);
    }

    @Test
    void getByRestaurantId() {
        MENU_MATCHER.assertMatch(menuService.getByRestaurantId(RESTAURANT_ID), menu1);
    }

    @Test
    void getWithDishes() {
        Menu menu = menuService.getWithDishes(MENU1_ID);
        MENU_WITH_DISHES_MATCHER.assertMatch(menu, menu1);
    }

    @Test
    void getByDateAndRestaurant() {
        MENU_MATCHER.assertMatch(menuService.getByDateAndRestaurant(LocalDate.now(),RESTAURANT_ID), menu1);
    }

    @Test
    void create() {
        MenuTo createdMenuTo = MenuUtil.createTo(MenuTestData.getNew());
        createdMenuTo.setRestaurantId(RESTAURANT_ID);
        Menu created = menuService.create(createdMenuTo);
        int newId = created.id();
        Menu newDish = MenuTestData.getNew();
        newDish.setId(newId);
        MENU_MATCHER.assertMatch(created, newDish);
        MENU_MATCHER.assertMatch(menuService.get(newId), newDish);
    }

    @Test
    void update() {
        Menu updated = MenuTestData.getUpdated();
        MenuTo updatedDishTo = MenuUtil.createTo(updated);
        updatedDishTo.setRestaurantId(RESTAURANT_ID);
        menuService.update(updatedDishTo, updatedDishTo.getId());
        MENU_MATCHER.assertMatch(menuService.get(DISH1_ID), MenuTestData.getUpdated());
    }

    @Test
    void delete() {
        menuService.delete(MENU1_ID);
        assertThrows(NotFoundException.class, () -> menuService.get(DISH1_ID));
    }
}

package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Menu;
import ru.graduation.testdata.MenuTestData;
import ru.graduation.web.exeption.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.DishTestData.DISH1_ID;
import static ru.graduation.testdata.MenuTestData.*;
import static ru.graduation.testdata.RestaurantTestData.NOT_FOUND_RESTAURANT;
import static ru.graduation.testdata.RestaurantTestData.RESTAURANT_ID;

@SpringBootTest
@Transactional
public class MenuServiceTest {

    @Autowired
    protected MenuService menuService;

    @Test
    void delete() {
        menuService.delete(MENU1_ID);
        assertThrows(NotFoundException.class, () -> menuService.get(DISH1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> menuService.delete(NOT_FOUND_MENU));
    }

    @Test
    void get() {
        MENU_MATCHER.assertMatch(menuService.get(MENU1_ID), menu1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> menuService.get(NOT_FOUND_MENU));
    }

    @Test
    void getByRestaurantId() {
        MENU_MATCHER.assertMatch(menuService.getByRestaurantId(RESTAURANT_ID), menus);
    }

    @Test
    void update() {
        Menu updated = MenuTestData.getUpdated();
        menuService.update(updated, RESTAURANT_ID);
        MENU_MATCHER.assertMatch(menuService.get(MENU1_ID), MenuTestData.getUpdated());
    }

    @Test
    void create() {
        Menu created = menuService.create(MenuTestData.getNew(), RESTAURANT_ID);
        int newId = created.id();
        Menu newMenu = MenuTestData.getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newId), newMenu);
    }

    @Test
    void getWithDishes() {
        Menu menu = menuService.get(MENU1_ID);
        MENU_WITH_DISHES_MATCHER.assertMatch(menu, menu1);
    }
}

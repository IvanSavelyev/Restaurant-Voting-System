package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.TimingExtension;
import ru.graduation.model.Menu;
import ru.graduation.web.exeption.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.DishTestData.DISH1_ID;
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
        Menu updated = getUpdated();
        menuService.update(updated, RESTAURANT_ID);
        MENU_MATCHER.assertMatch(menuService.get(MENU1_ID), getUpdated());
    }

    @Test
    void create() {
        Menu created = menuService.create(getNew(), RESTAURANT_ID);
        int newId = created.id();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newId), newMenu);
    }

    @Test
    void getWithDishes() {
        Menu menu = menuService.getWithDishes(MENU1_ID);
        MENU_WITH_DISHES_MATCHER.assertMatch(menu, menu1);
    }
}

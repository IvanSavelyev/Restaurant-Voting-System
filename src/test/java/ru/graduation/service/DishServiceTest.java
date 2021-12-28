package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Dish;
import ru.graduation.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.DishTestData.*;
import static ru.graduation.testdata.MenuTestData.MENU1_ID;
import static ru.graduation.testdata.MenuTestData.NOT_FOUND_MENU;

@SpringBootTest
@Transactional
public class DishServiceTest {

    @Autowired
    protected DishService dishService;

    @Test
    void delete() {
        dishService.delete(DISH1_ID);
        assertThrows(NotFoundException.class, () -> dishService.get(DISH1_ID));
    }

    @Test
    void deleteNotFoundById() {
        assertThrows(NotFoundException.class, () -> dishService.delete(NOT_FOUND_DISH));
    }

    @Test
    void deleteByIdAndMenuId() {
        dishService.delete(DISH1_ID, MENU1_ID);
        assertThrows(NotFoundException.class, () -> dishService.get(DISH1_ID));
    }

    @Test
    void deleteNotFoundByIdAndMenuId() {
        assertThrows(NotFoundException.class, () -> dishService.delete(NOT_FOUND_DISH, NOT_FOUND_MENU));
    }

    @Test
    void get() {
        DISH_MATCHER.assertMatch(dishService.get(DISH1_ID), dish1);
    }

    @Test
    void getAllByMenuId() {
        DISH_MATCHER.assertMatch(dishService.getAllByMenuId(MENU1_ID), dishes);
    }

    @Test
    void update() {
        Dish updated = getUpdated();
        dishService.update(updated, MENU1_ID);
        DISH_MATCHER.assertMatch(dishService.get(DISH1_ID), getUpdated());
    }

    @Test
    void create() {
        Dish created = dishService.create(getNew(), MENU1_ID);
        int newId = created.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId), newDish);
    }
}

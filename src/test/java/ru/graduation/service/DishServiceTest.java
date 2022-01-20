package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.TimingExtension;
import ru.graduation.model.Dish;
import ru.graduation.web.exeption.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.DishTestData.*;
import static ru.graduation.testdata.MenuTestData.MENU1_ID;

@SpringBootTest
@Transactional
@ExtendWith(TimingExtension.class)
@ActiveProfiles("test")
public class DishServiceTest {

    @Autowired
    protected DishService dishService;

    @Test
    void delete() {
        dishService.delete(DISH1_ID);
        assertThrows(NotFoundException.class, () -> dishService.get(DISH1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> dishService.delete(NOT_FOUND_DISH));
    }

    @Test
    void deleteByIdAndMenuId() {
//        dishService.delete(DISH1_ID, MENU1_ID);
//        assertThrows(NotFoundException.class, () -> dishService.get(DISH1_ID));
    }

    @Test
    void deleteNotFoundByIdAndMenuId() {
//        assertThrows(NotFoundException.class, () -> dishService.delete(NOT_FOUND_DISH, NOT_FOUND_MENU));
    }

    @Test
    void get() {
        DISH_MATCHER.assertMatch(dishService.get(DISH1_ID), dish1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> dishService.get(NOT_FOUND_DISH));
    }

    @Test
    void getAllByMenuId() {
        DISH_MATCHER.assertMatch(dishService.getAllByMenuId(MENU1_ID), dishes);
    }

    @Test
    void update() {
//        Dish updated = getUpdated();
//        dishService.update(updated, MENU1_ID);
//        DISH_MATCHER.assertMatch(dishService.get(DISH1_ID), getUpdated());
    }

    @Test
    void create() {
//        Dish created = dishService.create(getNew(), MENU1_ID);
//        int newId = created.id();
//        Dish newDish = getNew();
//        newDish.setId(newId);
//        DISH_MATCHER.assertMatch(created, newDish);
//        DISH_MATCHER.assertMatch(dishService.get(newId), newDish);
    }
}

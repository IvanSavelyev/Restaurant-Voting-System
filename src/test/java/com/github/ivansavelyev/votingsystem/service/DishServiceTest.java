package com.github.ivansavelyev.votingsystem.service;

import com.github.ivansavelyev.votingsystem.TimingExtension;
import com.github.ivansavelyev.votingsystem.model.Dish;
import com.github.ivansavelyev.votingsystem.testdata.DishTestData;
import com.github.ivansavelyev.votingsystem.to.DishTo;
import com.github.ivansavelyev.votingsystem.util.DishUtil;
import com.github.ivansavelyev.votingsystem.web.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static com.github.ivansavelyev.votingsystem.testdata.MenuTestData.MENU1_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@ExtendWith(TimingExtension.class)
@ActiveProfiles("test")
public class DishServiceTest {

    @Autowired
    protected DishService dishService;

    @Test
    void delete() {
        dishService.delete(DishTestData.DISH1_ID);
        assertThrows(NotFoundException.class, () -> dishService.get(DishTestData.DISH1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> dishService.delete(DishTestData.NOT_FOUND_DISH));
    }

    @Test
    void get() {
        DishTestData.DISH_MATCHER.assertMatch(dishService.get(DishTestData.DISH1_ID), DishTestData.dish1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> dishService.get(DishTestData.NOT_FOUND_DISH));
    }

    @Test
    void getAllByMenuId() {
        DishTestData.DISH_MATCHER.assertMatch(dishService.getAllByMenuId(MENU1_ID), DishTestData.dishes);
    }

    @Test
    void update() {
        Dish updated = DishTestData.getUpdated();
        DishTo updatedDishTo = DishUtil.createTo(updated);
        updatedDishTo.setMenuId(MENU1_ID);
        dishService.update(updatedDishTo, updatedDishTo.getId());
        DishTestData.DISH_MATCHER.assertMatch(dishService.get(DishTestData.DISH1_ID), DishTestData.getUpdated());
    }

    @Test
    void create() {
        DishTo createdDishTo = DishUtil.createTo(DishTestData.getNew());
        createdDishTo.setMenuId(MENU1_ID);
        Dish created = dishService.create(createdDishTo);
        int newId = created.id();
        Dish newDish = DishTestData.getNew();
        newDish.setId(newId);
        DishTestData.DISH_MATCHER.assertMatch(created, newDish);
        DishTestData.DISH_MATCHER.assertMatch(dishService.get(newId), newDish);
    }
}

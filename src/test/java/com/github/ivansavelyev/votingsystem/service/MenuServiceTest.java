package com.github.ivansavelyev.votingsystem.service;

import com.github.ivansavelyev.votingsystem.TimingExtension;
import com.github.ivansavelyev.votingsystem.model.Menu;
import com.github.ivansavelyev.votingsystem.testdata.DishTestData;
import com.github.ivansavelyev.votingsystem.testdata.MenuTestData;
import com.github.ivansavelyev.votingsystem.to.MenuTo;
import com.github.ivansavelyev.votingsystem.util.MenuUtil;
import com.github.ivansavelyev.votingsystem.web.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.github.ivansavelyev.votingsystem.testdata.MenuTestData.*;
import static com.github.ivansavelyev.votingsystem.testdata.RestaurantTestData.RESTAURANT_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void getCheckWithDish() {
        MENU_WITH_DISHES_MATCHER.assertMatch(menuService.get(MENU1_ID), menu1);
    }

    @Test
    void getByDateAndRestaurant() {
        MENU_MATCHER.assertMatch(menuService.getByDateAndRestaurant(LocalDate.now(), RESTAURANT_ID), menu1);
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
        MENU_MATCHER.assertMatch(menuService.get(DishTestData.DISH1_ID), MenuTestData.getUpdated());
    }

    @Test
    void delete() {
        menuService.delete(MENU1_ID);
        assertThrows(NotFoundException.class, () -> menuService.get(DishTestData.DISH1_ID));
    }
}

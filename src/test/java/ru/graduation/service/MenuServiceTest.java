package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.graduation.model.Menu;
import ru.graduation.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.DishTestData.DISH1_ID;
import static ru.graduation.testdata.MenuTestData.*;

@SpringBootTest
public class MenuServiceTest {

    @Autowired
    protected MenuService menuService;

    @Test
    void delete() {
        menuService.delete(MENU1_ID);
        assertThrows(NotFoundException.class, () -> menuService.get(DISH1_ID, 1));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> menuService.delete(NOT_FOUND));
    }

    @Test
    void get() {
        Menu actual = menuService.get(MENU1_ID);
        MENU_MATCHER.assertMatch(actual, menu1);
    }
}

package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.graduation.model.Dish;
import ru.graduation.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.graduation.testdata.DishTestData.*;

@SpringBootTest
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@Sql(scripts = "classpath:data.sql", config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class DishServiceTest {

    @Autowired
    protected DishService dishService;

//    @Test
//    void delete() {
//        dishService.delete(DISH1_ID, 1);
////        Dish dish = dishService.get(DISH1_ID);
//        assertThrows(NotFoundException.class, () -> dishService.getByMenuId(DISH1_ID, 1));
//    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> dishService.delete(NOT_FOUND));
    }

    @Test
    void get() {
        Dish actual = dishService.get(DISH1_ID);
        DISH_MATCHER.assertMatch(actual, dish1);
    }
}

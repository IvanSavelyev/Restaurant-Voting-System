package ru.graduation.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.graduation.TestUtil;
import ru.graduation.model.Dish;
import ru.graduation.service.DishService;
import ru.graduation.web.exeption.NotFoundException;
import ru.graduation.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.testdata.DishTestData.*;
import static ru.graduation.testdata.UserTestData.admin;
import static ru.graduation.web.controllers.dish.AdminDishController.ADMIN_DISH_REST_URL;

public class AdminDishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/" + ADMIN_DISH_REST_URL + "/";

    @Autowired
    private DishService dishService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH1_ID)
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND_DISH)
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getByMenuId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .param("menuId", "1")
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dishes));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + DISH1_ID)
                .param("menuId", "1")
                .with(TestUtil.userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> dishService.get(DISH1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + NOT_FOUND_DISH)
                .param("menuId", "1")
                .with(TestUtil.userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void update() throws Exception {
        Dish updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + DISH1_ID)
                .param("menuId", "1")
                .with(TestUtil.userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        DISH_MATCHER.assertMatch(dishService.get(DISH1_ID), getUpdated());
    }

    @Test
    void create() throws Exception {
        Dish newDish = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("menuId", "1")
                .with(TestUtil.userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andDo(print())
                .andExpect(status().isCreated());

        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(dishService.get(newId), newDish);
    }
}
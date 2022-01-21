package com.github.ivansavelyev.votingsystem.controller;


import com.github.ivansavelyev.votingsystem.TestUtil;
import com.github.ivansavelyev.votingsystem.model.Dish;
import com.github.ivansavelyev.votingsystem.service.DishService;
import com.github.ivansavelyev.votingsystem.testdata.DishTestData;
import com.github.ivansavelyev.votingsystem.to.DishTo;
import com.github.ivansavelyev.votingsystem.util.DishUtil;
import com.github.ivansavelyev.votingsystem.web.controllers.dish.AdminDishController;
import com.github.ivansavelyev.votingsystem.web.exeption.NotFoundException;
import com.github.ivansavelyev.votingsystem.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.ivansavelyev.votingsystem.testdata.MenuTestData.MENU1_ID;
import static com.github.ivansavelyev.votingsystem.testdata.UserTestData.admin;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AdminDishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/" + AdminDishController.ADMIN_DISH_REST_URL + "/";

    @Autowired
    private DishService dishService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DishTestData.DISH1_ID)
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DishTestData.DISH_MATCHER.contentJson(DishTestData.dish1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DishTestData.NOT_FOUND_DISH)
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
                .andExpect(DishTestData.DISH_MATCHER.contentJson(DishTestData.dishes));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + DishTestData.DISH1_ID)
                .param("menuId", "1")
                .with(TestUtil.userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> dishService.get(DishTestData.DISH1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + DishTestData.NOT_FOUND_DISH)
                .param("menuId", "1")
                .with(TestUtil.userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void update() throws Exception {
        DishTo updatedDishTo = DishUtil.createTo(DishTestData.getUpdated());
        updatedDishTo.setMenuId(MENU1_ID);
        perform(MockMvcRequestBuilders.put(REST_URL + DishTestData.DISH1_ID)
                .with(TestUtil.userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedDishTo)))
                .andDo(print())
                .andExpect(status().isNoContent());
        DishTestData.DISH_MATCHER.assertMatch(dishService.get(DishTestData.DISH1_ID), DishTestData.getUpdated());
    }

    @Test
    void create() throws Exception {
        DishTo newDishTo = DishUtil.createTo(DishTestData.getNew());
        newDishTo.setMenuId(MENU1_ID);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .with(TestUtil.userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDishTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        Dish created = DishTestData.DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDishTo.setId(newId);
        DishTestData.DISH_MATCHER.assertMatch(dishService.get(newId), DishUtil.createFromTo(newDishTo));
    }
}
package com.github.ivansavelyev.votingsystem.controller;

import com.github.ivansavelyev.votingsystem.TestUtil;
import com.github.ivansavelyev.votingsystem.model.Menu;
import com.github.ivansavelyev.votingsystem.service.MenuService;
import com.github.ivansavelyev.votingsystem.testdata.MenuTestData;
import com.github.ivansavelyev.votingsystem.to.MenuTo;
import com.github.ivansavelyev.votingsystem.util.MenuUtil;
import com.github.ivansavelyev.votingsystem.web.controllers.menu.AdminMenuController;
import com.github.ivansavelyev.votingsystem.web.exeption.NotFoundException;
import com.github.ivansavelyev.votingsystem.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.ivansavelyev.votingsystem.testdata.MenuTestData.*;
import static com.github.ivansavelyev.votingsystem.testdata.RestaurantTestData.RESTAURANT_ID;
import static com.github.ivansavelyev.votingsystem.testdata.UserTestData.admin;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AdminMenuControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/" + AdminMenuController.ADMIN_MENU_REST_URL + "/";

    @Autowired
    private MenuService menuService;

    @Test
    void update() throws Exception {
        MenuTo updatedMenuTo = MenuUtil.createTo(MenuTestData.getUpdated());
        updatedMenuTo.setRestaurantId(RESTAURANT_ID);
        perform(MockMvcRequestBuilders.put(REST_URL + MENU1_ID)
                .with(TestUtil.userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedMenuTo)))
                .andDo(print())
                .andExpect(status().isNoContent());
        MENU_MATCHER.assertMatch(menuService.get(MENU1_ID), MenuTestData.getUpdated());
    }

    @Test
    void create() throws Exception {
        MenuTo newMenuTo = MenuUtil.createTo(MenuTestData.getNew());
        newMenuTo.setRestaurantId(RESTAURANT_ID);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .with(TestUtil.userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenuTo)))
                .andDo(print())
                .andExpect(status().isCreated());
        Menu created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenuTo.setId(newId);
        MENU_MATCHER.assertMatch(menuService.get(newId), MenuUtil.createFromTo(newMenuTo));
    }

    @Test
    void getWithDishesByRestaurantIdAndDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL).param("restaurantId", "1").param("localDate", "")
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menu1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND_MENU)
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getByRestaurantId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL).param("restaurantId", "1")
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menu1));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MENU1_ID)
                .with(TestUtil.userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> menuService.get(MENU1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + NOT_FOUND_MENU)
                .with(TestUtil.userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MENU1_ID)
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_WITH_DISHES_MATCHER.contentJson(menu1));
    }
}

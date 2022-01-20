package ru.graduation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.graduation.TestUtil;
import ru.graduation.service.MenuService;
import ru.graduation.web.exeption.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.testdata.MenuTestData.*;
import static ru.graduation.testdata.UserTestData.admin;
import static ru.graduation.web.controllers.menu.AdminMenuController.ADMIN_MENU_REST_URL;

public class AdminMenuControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/" + ADMIN_MENU_REST_URL + "/";

    @Autowired
    private MenuService menuService;

    @Test
    void get() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + MENU1_ID)
//                .with(TestUtil.userHttpBasic(admin)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MENU_MATCHER.contentJson(menu1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND_MENU)
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAllMenusByRestaurantId() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL)
//                .param("restaurantId", "1")
//                .with(TestUtil.userHttpBasic(admin)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MENU_MATCHER.contentJson(menus));
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
    void update() throws Exception {
//        Menu updated = MenuTestData.getUpdated();
//        perform(MockMvcRequestBuilders.put(REST_URL + MENU1_ID)
//                .param("restaurantId", "1")
//                .with(TestUtil.userHttpBasic(admin))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(updated)))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//        MENU_MATCHER.assertMatch(menuService.get(MENU1_ID), MenuTestData.getUpdated());
    }

    @Test
    void create() throws Exception {
////        Menu newMenu = MenuTestData.getNew();
//        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
//                .param("restaurantId", "1")
//                .with(TestUtil.userHttpBasic(admin))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(newMenu)))
//                .andDo(print())
//                .andExpect(status().isCreated());
//
//        Menu created = MENU_MATCHER.readFromJson(action);
//        int newId = created.id();
//        newMenu.setId(newId);
//        MENU_MATCHER.assertMatch(menuService.get(newId), newMenu);
    }

    @Test
    void getWithDishes() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + MENU1_ID + "/with-dishes")
//                .with(TestUtil.userHttpBasic(admin)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MENU_WITH_DISHES_MATCHER.contentJson(menu1));
    }
}

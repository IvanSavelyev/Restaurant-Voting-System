package ru.graduation.web.controllers.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Menu;
import ru.graduation.service.MenuService;

import java.util.List;

@Slf4j
public abstract class AbstractMenuController {

    @Autowired
    protected MenuService menuService;

    public List<Menu> getAllMenusByRestaurantId(int restaurantId) {
        log.debug("Get menus with from restaurantId : {}", restaurantId);
        return menuService.getByRestaurantId(restaurantId);
    }

    public Menu getById(int id) {
        log.debug("Get menus with id : {}", id);
        return menuService.get(id);
    }
}
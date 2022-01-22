package com.github.ivansavelyev.votingsystem.web.controllers.menu;

import com.github.ivansavelyev.votingsystem.model.Menu;
import com.github.ivansavelyev.votingsystem.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public abstract class AbstractMenuController {

    @Autowired
    protected MenuService menuService;

    public Menu get(int id) {
        log.debug("Get menu with id : {}", id);
        return menuService.get(id);
    }

    public Menu getByRestaurantIdAndDate(LocalDate localDate, int restaurantId) {
        log.debug("Get menu with dishes from restaurantId: {} with date : {}", restaurantId, localDate);
        return menuService.getByDateAndRestaurant(localDate, restaurantId);
    }
}

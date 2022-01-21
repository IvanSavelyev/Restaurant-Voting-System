package ru.graduation.web.controllers.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Menu;
import ru.graduation.service.MenuService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public abstract class AbstractMenuController {

    @Autowired
    protected MenuService menuService;

    public Menu getByRestaurantId(int restaurantId) {
        log.debug("Get menu from restaurantId : {}", restaurantId);
        return menuService.getByRestaurantId(restaurantId);
    }

    public Menu get(int id) {
        log.debug("Get menu with id : {}", id);
        return menuService.get(id);
    }

    public Menu getWithDishes(int id) {
        log.debug("Get menu by id: {} with dishes", id);
        return menuService.getWithDishes(id);
    }

    public Menu getWithDishesByRestaurantIdAndDate(LocalDate localDate, int restaurantId) {
        log.debug("Get menu with dishes from restaurantId: {} with date : {}", restaurantId, localDate);
        return menuService.getByDateAndRestaurant(localDate, restaurantId);
    }
}

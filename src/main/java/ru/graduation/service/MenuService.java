package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Menu;
import ru.graduation.repository.MenuRepository;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.to.MenuTo;
import ru.graduation.util.MenuUtil;
import ru.graduation.util.ValidationUtil;
import ru.graduation.web.exeption.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;
import static ru.graduation.util.ValidationUtil.getFromOptional;

@Service("MenuService")
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private final RestaurantRepository restaurantRepository;

    public Menu get(int id) {
        return getFromOptional(menuRepository.findById(id), id);
    }

    public Menu getByRestaurantId(int restaurantId) {
        return checkNotFoundWithId(menuRepository.findByRestaurantId(restaurantId), restaurantId);
    }

    public Menu getWithDishes(int id) {
        return checkNotFoundWithId(menuRepository.getWithDishes(id), id);
    }

    public Menu getByDateAndRestaurant(LocalDate localDate, int restaurantId) {
        return menuRepository.getMenuByDateWithDishesAndRestaurantId(localDate, restaurantId);
    }

    public Menu create(MenuTo menuTo) {
        Menu menu = checkAndGet(menuTo);
        ValidationUtil.checkNew(menu);
        Integer restaurantId = menuTo.getRestaurantId();
        menu.setRestaurant(restaurantRepository.getById(restaurantId));
        return menuRepository.save(menu);
    }

    public void update(MenuTo menuTo, int id) {
        Menu menu = checkAndGet(menuTo);
        ValidationUtil.assureIdConsistent(menu, id);
        Integer restaurantId = menuTo.getRestaurantId();
        menu.setRestaurant(restaurantRepository.getById(restaurantId));
        menuRepository.save(menu);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id) != 0, id);
    }

    private Menu checkAndGet(MenuTo menuTo) {
        Assert.notNull(menuTo, "menuTo must not be null");
        return MenuUtil.createFromTo(menuTo);
    }
}

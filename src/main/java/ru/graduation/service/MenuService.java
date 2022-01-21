package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Menu;
import ru.graduation.repository.MenuRepository;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.web.exeption.NotFoundException;

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

    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        menu.setRestaurant(restaurantRepository.getById(restaurantId));
        return menuRepository.save(menu);
    }

    public void update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        menu.setRestaurant(restaurantRepository.getById(restaurantId));
        menuRepository.save(menu);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id) != 0, id);
    }

    public Menu getWithDishes(int id) {
        return checkNotFoundWithId(menuRepository.getWithDishes(id), id);
    }

    public Menu getWithDishesByRestaurantId(int restaurantId) {
        return checkNotFoundWithId(menuRepository.getWithDishes(restaurantId), restaurantId);
    }

}

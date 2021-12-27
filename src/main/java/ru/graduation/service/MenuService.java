package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Menu;
import ru.graduation.repository.DishRepository;
import ru.graduation.repository.MenuRepository;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.util.ValidationUtil;
import ru.graduation.util.exception.NotFoundException;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service("MenuService")
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private final RestaurantRepository restaurantRepository;

    public Menu getWithDishByMenuIdAndRestaurantId(int id, int restaurantId) {
        return checkNotFoundWithId(menuRepository.getWithDishByMenuIdAndRestaurantId(id, restaurantId), id);
    }

    public Menu get(int id) {
        return checkNotFoundWithId(menuRepository.findById(id).get(), id);
    }

    public List<Menu> getByRestaurantId(int restaurantId) {
        return checkNotFoundWithId(menuRepository.findByRestaurantId(restaurantId), restaurantId);
    }

    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        if(menu.isNew())
            return null;
        menu.setRestaurant(restaurantRepository.findById(restaurantId));
        return menuRepository.save(menu);
    }

    public void update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        menu.setRestaurant(restaurantRepository.findById(restaurantId));
        checkNotFoundWithId(menuRepository.save(menu), menu.id());
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id) != 0 , id);
    }
}

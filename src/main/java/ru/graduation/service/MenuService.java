package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Menu;
import ru.graduation.repository.MenuRepository;
import ru.graduation.util.exception.NotFoundException;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service("MenuService")
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private final RestaurantService restaurantService;

    public Menu get(int id) {
        return menuRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found with" + id));
    }

    public List<Menu> getByRestaurantId(int restaurantId) {
        return checkNotFoundWithId(menuRepository.findByRestaurantId(restaurantId), restaurantId);
    }

    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        menu.setRestaurant(restaurantService.get(restaurantId));
        return menuRepository.save(menu);
    }

    public void update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        menu.setRestaurant(restaurantService.get(restaurantId));
        checkNotFoundWithId(menuRepository.save(menu), menu.id());
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id) != 0, id);
    }
}

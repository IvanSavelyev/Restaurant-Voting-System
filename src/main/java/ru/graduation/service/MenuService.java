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

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private final RestaurantRepository restaurantRepository;

    public Menu get(int id, int restaurantId) {
        return checkNotFoundWithId(menuRepository.getWithDishByRestaurantId(id, restaurantId), id);
    }

    public Menu get(int id) {
        return checkNotFoundWithId(menuRepository.get(id), id);
    }

    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        if(menu.isNew())
            return null;
        menu.setRestaurant(restaurantRepository.getById(restaurantId));
        return menuRepository.save(menu);
    }

    public void update(Menu menu, int restaurant_id) {
        Assert.notNull(menu, "menu must not be null");
        menu.setRestaurant(restaurantRepository.getById(restaurant_id));
        checkNotFoundWithId(menuRepository.save(menu), menu.id());
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id) != 0 , id);
    }


//    public Dish create(Dish dish, int menuId, int restaurantId) {
//        ValidationUtil.checkNew(dish);
//        if (!dish.isNew() && get(dish.id(), restaurantId) == null) {
//            return null;
//        }
//        Menu menu = menuRepository.findMenuByIdAndRestaurantId(menuId, restaurantId);
//        if(menu == null){
//            Menu createdMenu = new Menu();
//            createdMenu.setRestaurant(restaurantRepository.getById(restaurantId));
//        }
//
//        dish.setMenu(menuRepository.getById(menuId));
//        return dishRepository.save(dish);
//    }
}

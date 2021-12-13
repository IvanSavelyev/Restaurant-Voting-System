package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.graduation.model.Dish;
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
public class DishService {

    private final DishRepository dishRepository;

    private final MenuRepository menuRepository;

    private final RestaurantRepository restaurantRepository;

    public List<Dish> getAllByMenuId(int menuId) {
        return dishRepository.findAllByMenuId(menuId);
    }

    public Dish get(int id) {
        return checkNotFoundWithId(dishRepository.getById(id), id);
    }

    public Dish get(int id, int menuId) {
        return checkNotFoundWithId(dishRepository.findDishByIdAndMenuId(id, menuId), id);
    }

    public void delete(int id, int menuId) throws NotFoundException {
        checkNotFoundWithId(dishRepository.deleteByIdAndMenuId(id, menuId) != 0 , id);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dishRepository.deleteById(id) != 0 , id);
    }

    public Dish create(Dish dish, int menuId) {
        ValidationUtil.checkNew(dish);
        if (!dish.isNew() && get(dish.id(), menuId) == null) {
            return null;
        }
        dish.setMenu(menuRepository.getById(menuId));
        return dishRepository.save(dish);
    }


    public Dish create(Dish dish, int menuId, int restaurantId) {
        ValidationUtil.checkNew(dish);
        if (!dish.isNew() && get(dish.id(), restaurantId) == null) {
            return null;
        }
        Menu menu = menuRepository.findMenuByIdAndRestaurantId(menuId, restaurantId);
        if(menu == null){
            Menu createdMenu = new Menu();
            createdMenu.setRestaurant(restaurantRepository.getById(restaurantId));
        }

        dish.setMenu(menuRepository.getById(menuId));
        return dishRepository.save(dish);
    }
}

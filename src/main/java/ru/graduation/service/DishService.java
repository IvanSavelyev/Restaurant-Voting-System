package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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

    public Dish create(Dish dish, int menuId) {
        ValidationUtil.checkNew(dish);
        dish.setMenu(menuRepository.getById(menuId));
        return checkNotFoundWithId(dishRepository.save(dish), dish.id());
    }

    public Dish update(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setMenu(menuRepository.getById(menuId));
        return checkNotFoundWithId(dishRepository.save(dish),dish.id());
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dishRepository.deleteById(id) != 0 , id);
    }

    public void delete(int id, int menuId) throws NotFoundException {
        checkNotFoundWithId(dishRepository.deleteByIdAndMenuId(id, menuId) != 0 , id);
    }

    public Dish get(int id) {
        return checkNotFoundWithId(dishRepository.getById(id), id);
    }

    public Dish getByMenuId(int id, int menuId) {
        return checkNotFoundWithId(dishRepository.findDishByIdaAndMenuId(id, menuId), id);
    }

    public List<Dish> getAllByMenuId(int menuId) {
        return dishRepository.findAllByMenuId(menuId);
    }
}

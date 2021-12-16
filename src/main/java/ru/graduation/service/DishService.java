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

    public List<Dish> getAllByMenuId(int menuId) {
        return dishRepository.findAllByMenuId(menuId);
    }

    public Dish get(int id) {
        return checkNotFoundWithId(dishRepository.getById(id), id);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dishRepository.deleteById(id) != 0 , id);
    }

    public Dish create(Dish dish, int menuId) {
        ValidationUtil.checkNew(dish);
        dish.setMenu(menuRepository.getById(menuId));
        return dishRepository.save(dish);
    }

    public Dish update(Dish dish, int menuId) {
        dish.setMenu(menuRepository.getById(menuId));
        return dishRepository.save(dish);
    }
}

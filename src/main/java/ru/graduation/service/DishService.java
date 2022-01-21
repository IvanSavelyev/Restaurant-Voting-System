package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;
import ru.graduation.repository.MenuRepository;
import ru.graduation.to.DishTo;
import ru.graduation.util.DishUtil;
import ru.graduation.util.ValidationUtil;
import ru.graduation.web.exeption.NotFoundException;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;
import static ru.graduation.util.ValidationUtil.getFromOptional;

@Service("DishService")
@AllArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    private final MenuRepository menuRepository;

    public Dish get(int id) {
        return getFromOptional(dishRepository.findById(id), id);
    }

    public List<Dish> getAllByMenuId(int menuId) {
        return dishRepository.findAllByMenuId(menuId);
    }

    public Dish create(DishTo dishTo) {
        Dish dish = checkAndGet(dishTo);
        ValidationUtil.checkNew(dish);
        Integer menuId = dishTo.getMenuId();
        dish.setMenu(menuRepository.getById(menuId));
        return dishRepository.save(dish);
    }

    public void update(DishTo dishTo, int id) {
        Dish dish = checkAndGet(dishTo);
        ValidationUtil.assureIdConsistent(dish, id);
        Integer menuId = dishTo.getMenuId();
        dish.setMenu(menuRepository.getById(menuId));
        dishRepository.save(dish);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dishRepository.deleteById(id) != 0, id);
    }

    private Dish checkAndGet(DishTo dishTo){
        Assert.notNull(dishTo, "dishTo must not be null");
        return DishUtil.createFromTo(dishTo);
    }
}

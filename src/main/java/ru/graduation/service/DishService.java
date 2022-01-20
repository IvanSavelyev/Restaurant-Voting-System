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

    public Dish create(DishTo dishTo) {
        Assert.notNull(dishTo, "dishTo must not be null");
        Dish dish = DishUtil.createFromTo(dishTo);
        Integer menuId = dishTo.getMenuId();
        ValidationUtil.checkNew(DishUtil.createFromTo(dishTo));
        dish.setMenu(menuRepository.getById(menuId));
        return dishRepository.save(dish);
    }

    public void update(DishTo dishTo, int id) {
        Assert.notNull(dishTo, "dishTo must not be null");
        Dish dish = DishUtil.createFromTo(dishTo);
        dish.setId(id);
        Integer menuId = dishTo.getMenuId();
        ValidationUtil.assureIdConsistent(dish, dish.getId());
        dish.setMenu(menuRepository.getById(menuId));
        dishRepository.save(dish);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dishRepository.deleteById(id) != 0, id);
    }

    public Dish get(int id) {
        return getFromOptional(dishRepository.findById(id), id);
    }

    public List<Dish> getAllByMenuId(int menuId) {
        return dishRepository.findAllByMenuId(menuId);
    }
}

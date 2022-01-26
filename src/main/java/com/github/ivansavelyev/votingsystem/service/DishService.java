package com.github.ivansavelyev.votingsystem.service;

import com.github.ivansavelyev.votingsystem.model.Dish;
import com.github.ivansavelyev.votingsystem.repository.DishRepository;
import com.github.ivansavelyev.votingsystem.repository.MenuRepository;
import com.github.ivansavelyev.votingsystem.to.DishTo;
import com.github.ivansavelyev.votingsystem.util.DishUtil;
import com.github.ivansavelyev.votingsystem.web.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;

import static com.github.ivansavelyev.votingsystem.util.ValidationUtil.checkNotFoundWithId;
import static com.github.ivansavelyev.votingsystem.util.ValidationUtil.getFromOptional;


@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    private final MenuRepository menuRepository;

    public Dish get(int id) {
        return getFromOptional(dishRepository.findById(id), id);
    }

    public Set<Dish> getAllByMenuId(int menuId) {
        return dishRepository.findAllByMenuId(menuId);
    }

    public Dish create(DishTo dishTo) {
        Dish dish = checkAndGet(dishTo);
        Integer menuId = dishTo.getMenuId();
        dish.setMenu(menuRepository.getById(menuId));
        return dishRepository.save(dish);
    }

    public void update(DishTo dishTo, int id) {
        Dish dish = checkAndGet(dishTo);
        Integer menuId = dishTo.getMenuId();
        dish.setMenu(menuRepository.getById(menuId));
        dishRepository.save(dish);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dishRepository.deleteById(id) != 0, id);
    }

    private Dish checkAndGet(DishTo dishTo) {
        Assert.notNull(dishTo, "dishTo must not be null");
        return DishUtil.createFromTo(dishTo);
    }
}

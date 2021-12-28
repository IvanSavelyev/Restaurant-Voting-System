package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;
import ru.graduation.repository.MenuRepository;
import ru.graduation.util.exception.NotFoundException;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service("DishService")
@AllArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    private final MenuRepository menuRepository;

    public Dish create(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setMenu(menuRepository.findById(menuId).orElseThrow(()-> new NotFoundException("Not found with " + menuId)));
        return checkNotFoundWithId(dishRepository.save(dish), dish.id());
    }

    public void update(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setMenu(menuRepository.findById(menuId).orElseThrow(()-> new NotFoundException("Not found with " + menuId)));
        checkNotFoundWithId(dishRepository.save(dish), dish.id());
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dishRepository.deleteById(id) != 0, id);
    }

    public void delete(int id, int menuId) throws NotFoundException {
        checkNotFoundWithId(dishRepository.deleteByIdAndMenuId(id, menuId) != 0, id);
    }

    public Dish get(int id) {
        return dishRepository.findById(id).orElseThrow(()-> new NotFoundException("Not found with" + id));
    }

    public List<Dish> getAllByMenuId(int menuId) {
        return dishRepository.findAllByMenuId(menuId);
    }
}

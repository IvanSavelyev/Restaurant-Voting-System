package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.graduation.model.Dish;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.DishRepository;
import ru.graduation.repository.MenuRepository;

import javax.persistence.criteria.CriteriaBuilder;

@Service
@AllArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    private final MenuRepository menuRepository;

    public Dish getById(Integer id){
        return dishRepository.findById(id).get();
    }

    public void delete(Integer id, Integer menuId){
        dishRepository.deleteByIdAndMenuId(id, menuId);
    }

    public Dish save(Dish dish){
        return dishRepository.save(dish);
    }

}

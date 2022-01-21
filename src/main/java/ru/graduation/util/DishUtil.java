package ru.graduation.util;

import lombok.experimental.UtilityClass;
import ru.graduation.model.Dish;
import ru.graduation.to.DishTo;

@UtilityClass
public class DishUtil {

    public static Dish createFromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getName(), dishTo.getPrice());
    }

    public static DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }
}
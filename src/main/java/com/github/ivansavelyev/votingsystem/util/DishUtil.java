package com.github.ivansavelyev.votingsystem.util;


import com.github.ivansavelyev.votingsystem.model.Dish;
import com.github.ivansavelyev.votingsystem.to.DishTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DishUtil {

    public static Dish createFromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getName(), dishTo.getPrice());
    }

    public static DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }
}

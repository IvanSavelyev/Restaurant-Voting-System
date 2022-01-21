package ru.graduation.util;

import lombok.experimental.UtilityClass;
import ru.graduation.model.Dish;
import ru.graduation.model.Menu;
import ru.graduation.to.DishTo;
import ru.graduation.to.MenuTo;

@UtilityClass
public class MenuUtil {

    public static Menu createFromTo(MenuTo menuTo) {
        return new Menu(menuTo.getId(), menuTo.getDate());
    }

    public static MenuTo createTo(Menu menu) {
        return new MenuTo(menu.getId(), menu.getDate());
    }

}

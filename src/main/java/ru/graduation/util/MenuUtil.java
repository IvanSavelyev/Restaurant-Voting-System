package ru.graduation.util;

import lombok.experimental.UtilityClass;
import ru.graduation.model.Menu;
import ru.graduation.to.MenuTo;

@UtilityClass
public class MenuUtil {

    public static MenuTo createFrom(Menu menu) {
        return new MenuTo(menu.id(), menu.getName(), menu.getDate(), menu.getDishes());
    }
}

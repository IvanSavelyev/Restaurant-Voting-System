package com.github.ivansavelyev.votingsystem.util;


import com.github.ivansavelyev.votingsystem.model.Menu;
import com.github.ivansavelyev.votingsystem.to.MenuTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MenuUtil {

    public static Menu createFromTo(MenuTo menuTo) {
        return new Menu(menuTo.getId(), menuTo.getDate());
    }

    public static MenuTo createTo(Menu menu) {
        return new MenuTo(menu.getId(), menu.getDate());
    }

}

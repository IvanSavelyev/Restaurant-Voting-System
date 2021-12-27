package ru.graduation.testdata;

import ru.graduation.MatcherFactory;
import ru.graduation.model.Menu;

import java.time.LocalDate;

public class MenuTestData {
    //    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "menu");
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant", "dishes");

    public static final int MENU1_ID = 1;
    public static final int MENU2_ID = 2;
    public static final int NOT_FOUND = 100;

//    public static final Menu menu1 = new Menu(MENU1_ID, "BAR MENU", LocalDate.of(2021,11,15));
}

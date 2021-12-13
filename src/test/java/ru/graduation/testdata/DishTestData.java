package ru.graduation.testdata;

import ru.graduation.MatcherFactory;
import ru.graduation.model.Dish;

public class DishTestData {
    //    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "menu");
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "menu");

    public static final int DISH1_ID = 1;
    public static final int DISH2_ID = 2;
    public static final int NOT_FOUND = 100;

    public static final Dish dish1 = new Dish(DISH1_ID, "COBBER", 1000.5);
//    public static final Dish dish2 = new Dish(DISH2_ID, "COBBER", 900.51);
}

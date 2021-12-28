package ru.graduation.testdata;

import ru.graduation.MatcherFactory;
import ru.graduation.model.Dish;

import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "menu");

    public static final int DISH1_ID = 1;
    public static final int NOT_FOUND_DISH = 1000;

    public static final Dish dish1 = new Dish(DISH1_ID, "COBBER", 1000.5F);
    public static final Dish dish4 = new Dish(DISH1_ID+3, "Pork pie", 321.52F);
    public static final Dish dish7 = new Dish(DISH1_ID+6, "Stargazy pie", 313.54F);
    public static final Dish dish10 = new Dish(DISH1_ID+9, "Sunday roast", 554.58F);
    public static final Dish dish13 = new Dish(DISH1_ID+12, "Summer pudding", 111.52F);
    public static final Dish dish16 = new Dish(DISH1_ID+15, "Bangers and mash", 444.53F);

    public static Dish getUpdated(){
        return new Dish(DISH1_ID, "Updated", 999.1F);
    }

    public static Dish getNew(){
        return new Dish(null, "New", 999.1F);
    }

    public static final List<Dish> dishes = List.of(dish1, dish4, dish7, dish10, dish13, dish16);
}

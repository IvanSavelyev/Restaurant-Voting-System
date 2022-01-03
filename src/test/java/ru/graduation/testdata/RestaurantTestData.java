package ru.graduation.testdata;

import ru.graduation.MatcherFactory;
import ru.graduation.model.Restaurant;

import java.util.List;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class);

    public static final int RESTAURANT_ID = 1;
    public static final int NOT_FOUND_RESTAURANT = 3000;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "ASHOT DINNER");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_ID + 1, "IVAN LUNCHES");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT_ID + 2, "USER DISHES");
    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3);

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Updated");
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }
}

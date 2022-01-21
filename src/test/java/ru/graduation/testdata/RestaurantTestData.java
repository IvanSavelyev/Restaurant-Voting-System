package ru.graduation.testdata;

import ru.graduation.MatcherFactory;
import ru.graduation.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.testdata.MenuTestData.menu1;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu");

    public static final int RESTAURANT_ID = 1;
    public static final int NOT_FOUND_RESTAURANT = 3000;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "ASHOT DINNER");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_ID + 1, "IVAN LUNCHES");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT_ID + 2, "USER DISHES");
    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3);

    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER_WITH_MENU_AND_DISHES =
            MatcherFactory.usingAssertions(Restaurant.class,
//     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("menu.restaurant", "menu.dishes").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });
    static {
        restaurant1.setMenu(List.of(menu1));
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Updated");
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }
}

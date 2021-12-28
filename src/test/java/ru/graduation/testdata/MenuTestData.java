package ru.graduation.testdata;

import ru.graduation.MatcherFactory;
import ru.graduation.model.Dish;
import ru.graduation.model.Menu;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant", "dishes");

    public static MatcherFactory.Matcher<Menu> MENU_WITH_DISHES_MATCHER =
            MatcherFactory.usingAssertions(Menu.class,
//     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("restaurant", "dishes.menu").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });


    public static final int MENU1_ID = 1;
    public static final int NOT_FOUND_MENU = 2000;

    public static final Menu menu1 = new Menu(MENU1_ID, "BAR MENU", LocalDate.now());
    public static final Menu menu2 = new Menu(MENU1_ID+1, "MAIN MENU", LocalDate.now());
    public static final Menu menu3 = new Menu(MENU1_ID+2, "SNACK MENU", LocalDate.now());
    public static final Menu menu4 = new Menu(MENU1_ID+3, "DESSERTS MENU", LocalDate.now());

    static {
        menu1.setDishes(DishTestData.dishes);
    }

    public static final List<Menu> menus = List.of(menu1, menu2, menu3, menu4);

    public static Menu getUpdated(){
        return new Menu(MENU1_ID, "Updated", LocalDate.of(2030, 12,12));
    }

    public static Menu getNew(){
        return new Menu(null, "New", LocalDate.of(2040, 12,12));
    }
}

package ru.graduation.testdata;

import ru.graduation.MatcherFactory;
import ru.graduation.model.Menu;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant", "dishes");
    public static final int MENU1_ID = 1;
    public static final int NOT_FOUND_MENU = 2000;
    public static final Menu menu1 = new Menu(MENU1_ID, LocalDate.now());

    public static MatcherFactory.Matcher<Menu> MENU_WITH_DISHES_MATCHER =
            MatcherFactory.usingAssertions(Menu.class,
//     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("restaurant", "dishes.menu").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    static {
        menu1.setDishes(DishTestData.dishes);
    }

    public static Menu getUpdated() {
        return new Menu(MENU1_ID, LocalDate.of(2030, 12, 12));
    }

    public static Menu getNew() {
        return new Menu(null, LocalDate.of(2040, 12, 12));
    }
}

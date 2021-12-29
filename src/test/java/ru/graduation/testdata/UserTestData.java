package ru.graduation.testdata;

import ru.graduation.MatcherFactory;
import ru.graduation.model.Role;
import ru.graduation.model.User;
import ru.graduation.model.Vote;
import ru.graduation.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "password");

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final int USER_NOT_FOUND = 4000;
    public static final String USER_MAIL = "user@javaops.ru";
    public static final String ADMIN_MAIL = "admin@javaops.ru";

    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "admin", Role.ADMIN, Role.USER);
    public static final User user1 = new User(USER_ID+2, "User1", USER_MAIL.substring(0,4) + 1 + USER_MAIL.substring(4,USER_MAIL.length()), "password1", Role.USER);
    public static final User user2 = new User(USER_ID+3, "User2", USER_MAIL.substring(0,4) + 2 + USER_MAIL.substring(4,USER_MAIL.length()), "password2", Role.USER);
    public static final User user3 = new User(USER_ID+4, "User3", USER_MAIL.substring(0,4) + 3 + USER_MAIL.substring(4,USER_MAIL.length()), "password3", Role.USER);
    public static final User user4 = new User(USER_ID+5, "User4", USER_MAIL.substring(0,4) + 4 + USER_MAIL.substring(4,USER_MAIL.length()), "password4", Role.USER);
    public static final User user5 = new User(USER_ID+6, "User5", USER_MAIL.substring(0,4) + 5 + USER_MAIL.substring(4,USER_MAIL.length()), "password5", Role.USER);

    public static final List<User> users = List.of(user, admin, user1, user2, user3, user4, user5);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass",  Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(USER_ID, "UpdatedName", USER_MAIL, "newPass",  Collections.singleton(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
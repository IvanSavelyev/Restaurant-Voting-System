package com.github.ivansavelyev.votingsystem.testdata;

import com.github.ivansavelyev.votingsystem.MatcherFactory;
import com.github.ivansavelyev.votingsystem.model.Role;
import com.github.ivansavelyev.votingsystem.model.User;
import com.github.ivansavelyev.votingsystem.web.json.JsonUtil;

import java.util.Collections;
import java.util.List;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "password");

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final int USER_NOT_FOUND = 4000;
    public static final String USER_MAIL = "user@javaops.ru";
    public static final String ADMIN_MAIL = "admin@javaops.ru";

    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "Admin", Role.ADMIN, Role.USER);
    public static final User user1 = new User(USER_ID + 2, "User1", USER_MAIL.substring(0, 4) + 1 + USER_MAIL.substring(4), "password1", Role.USER);
    public static final User user2 = new User(USER_ID + 3, "User2", USER_MAIL.substring(0, 4) + 2 + USER_MAIL.substring(4), "password2", Role.USER);
    public static final User user3 = new User(USER_ID + 4, "User3", USER_MAIL.substring(0, 4) + 3 + USER_MAIL.substring(4), "password3", Role.USER);
    public static final User user4 = new User(USER_ID + 5, "User4", USER_MAIL.substring(0, 4) + 4 + USER_MAIL.substring(4), "password4", Role.USER);
    public static final User user5 = new User(USER_ID + 6, "User5", USER_MAIL.substring(0, 4) + 5 + USER_MAIL.substring(4), "password5", Role.USER);

    public static final List<User> users = List.of(user, admin, user1, user2, user3, user4, user5);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(USER_ID, "UpdatedName", USER_MAIL, "upPass", Collections.singleton(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
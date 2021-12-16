package ru.graduation.util;

import ru.graduation.model.Role;
import ru.graduation.model.User;

public class UserUtil {

    public static User createNewFromTo(ru.javawebinar.topjava.to.UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static ru.javawebinar.topjava.to.UserTo asTo(User user) {
        return new ru.javawebinar.topjava.to.UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static User updateFromTo(User user, ru.javawebinar.topjava.to.UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
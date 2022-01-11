package ru.graduation.util;

import lombok.experimental.UtilityClass;
import ru.graduation.model.User;
import ru.graduation.to.UserTo;

import static ru.graduation.config.WebSecurityConfig.PASSWORD_ENCODER;

@UtilityClass
public class UserUtil {

    public static User updateFromTo(User user, UserTo userTo) {
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
package com.github.ivansavelyev.votingsystem.util;

import com.github.ivansavelyev.votingsystem.config.WebSecurityConfig;
import com.github.ivansavelyev.votingsystem.model.User;
import com.github.ivansavelyev.votingsystem.to.UserTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtil {

    public static User updateFromTo(User user, UserTo userTo) {
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(WebSecurityConfig.PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
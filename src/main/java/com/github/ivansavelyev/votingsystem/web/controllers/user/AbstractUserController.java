package com.github.ivansavelyev.votingsystem.web.controllers.user;

import com.github.ivansavelyev.votingsystem.model.User;
import com.github.ivansavelyev.votingsystem.service.UserService;
import com.github.ivansavelyev.votingsystem.util.SecurityUtil;
import com.github.ivansavelyev.votingsystem.util.UserUtil;
import com.github.ivansavelyev.votingsystem.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.ivansavelyev.votingsystem.util.ValidationUtil.checkNew;

public class AbstractUserController {

    @Autowired
    protected UserService userService;

    public User get(int id) {
        return userService.get(id);
    }

    public User create(User user) {
        checkNew(user);
        return prepareAndSave(user);
    }

    public void update(User user) {
        User oldUser = SecurityUtil.authUser();
        ValidationUtil.assureIdConsistent(user, oldUser.id());
        user.setRoles(oldUser.getRoles());
        if (user.getPassword() == null) {
            user.setPassword(oldUser.getPassword());
        }
        userService.create(user);
    }

    protected User prepareAndSave(User user) {
        return userService.create(UserUtil.prepareToSave(user));
    }
}

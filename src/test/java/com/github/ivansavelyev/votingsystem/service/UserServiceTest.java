package com.github.ivansavelyev.votingsystem.service;

import com.github.ivansavelyev.votingsystem.TimingExtension;
import com.github.ivansavelyev.votingsystem.model.Role;
import com.github.ivansavelyev.votingsystem.model.User;
import com.github.ivansavelyev.votingsystem.testdata.UserTestData;
import com.github.ivansavelyev.votingsystem.web.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@ExtendWith(TimingExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    protected UserService userService;

    @Test
    public void create() {
        User created = userService.create(UserTestData.getNew());
        int newId = created.id();
        User newUser = UserTestData.getNew();
        newUser.setId(newId);
        UserTestData.USER_MATCHER.assertMatch(created, newUser);
        UserTestData.USER_MATCHER.assertMatch(userService.get(newId), newUser);
    }

    @Test
    void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                userService.create(new User(null, "Duplicate", UserTestData.ADMIN_MAIL, "newPass", Role.USER)));
    }

    @Test
    void delete() {
        userService.delete(UserTestData.USER_ID);
        assertThrows(NotFoundException.class, () -> userService.get(UserTestData.USER_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> userService.delete(UserTestData.USER_NOT_FOUND));
    }

    @Test
    void get() {
        User user = userService.get(UserTestData.ADMIN_ID);
        UserTestData.USER_MATCHER.assertMatch(user, UserTestData.admin);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> userService.get(UserTestData.USER_NOT_FOUND));
    }

    @Test
    void getByEmail() {
        User user = userService.getByEmail("admin@javaops.ru");
        UserTestData.USER_MATCHER.assertMatch(user, UserTestData.admin);
    }

    @Test
    void update() {
        User updated = UserTestData.getUpdated();
        userService.update(updated);
        UserTestData.USER_MATCHER.assertMatch(userService.get(UserTestData.USER_ID), UserTestData.getUpdated());
    }

    @Test
    void getAll() {
        List<User> all = userService.getAll();
        UserTestData.USER_MATCHER.assertMatch(all, UserTestData.users);
    }
}

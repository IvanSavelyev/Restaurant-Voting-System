package ru.graduation;

import lombok.Getter;
import lombok.ToString;
import ru.graduation.model.User;

import javax.validation.constraints.NotNull;

@Getter
@ToString(of = "user")
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthUser(@NotNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }
}
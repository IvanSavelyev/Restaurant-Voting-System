package ru.graduation;

import lombok.Getter;
import lombok.ToString;
import ru.graduation.model.User;
import ru.graduation.to.UserTo;
import ru.graduation.util.UserUtil;

import java.io.Serial;

@Getter
@ToString(of = "user")
public class AuthUser extends org.springframework.security.core.userdetails.User {

    @Serial
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoles());
        setTo(UserUtil.asTo(user));
    }

//    public int getId() {
//        return userTo.id();
//    }

    public void setTo(UserTo newTo) {
        newTo.setPassword(null);
        userTo = newTo;
    }

//    public UserTo getUserTo() {
//        return userTo;
//    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}
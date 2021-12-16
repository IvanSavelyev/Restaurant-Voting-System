package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.graduation.model.User;
import ru.graduation.repository.UserRepository;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User get(int id) {
        return checkNotFoundWithId(userRepository.getById(id), id);
    }
}

package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.graduation.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
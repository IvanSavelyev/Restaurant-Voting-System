package ru.graduation.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.User;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=?1")
    int delete(int id);

    User getByEmail(String email);

    @RestResource(rel = "by-email", path = "by-email")
    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    @Cacheable("users")
    Optional<User> findByEmailIgnoreCase(String email);
}

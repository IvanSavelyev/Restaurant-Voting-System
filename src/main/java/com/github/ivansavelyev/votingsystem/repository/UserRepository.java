package com.github.ivansavelyev.votingsystem.repository;

import com.github.ivansavelyev.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=?1")
    int delete(int id);

    User getByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);
}

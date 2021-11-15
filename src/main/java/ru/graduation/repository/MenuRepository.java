package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.graduation.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
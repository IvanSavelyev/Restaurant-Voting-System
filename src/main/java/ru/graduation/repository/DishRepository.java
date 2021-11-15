package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.graduation.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
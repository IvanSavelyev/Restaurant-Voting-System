package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.graduation.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
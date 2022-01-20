package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Menu;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu findByRestaurantId(int restaurantId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=?1")
    int delete(int id);

    @Transactional
    Menu save(Menu menu);

    @Query("SELECT m FROM Menu m JOIN FETCH m.dishes WHERE m.id=?1")
    Menu getWithDishes(int id);
}
package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> getByRestaurantId(int restaurantId);

    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant JOIN FETCH m.dishes WHERE m.restaurant.id=?2")
    List<Menu> getAllByRestaurant(int restaurantId);

    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant JOIN FETCH m.dishes WHERE m.id=?1 AND m.restaurant.id=?2")
    Menu getByIdRestaurantAndRestaurantId(int id, int restaurantId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=?1")
    @Secured(("ROLE_ADMIN"))
    int deleteById(int id);

    @Modifying
    @Transactional
    @Secured(("ROLE_ADMIN"))
    int save(int id);
}
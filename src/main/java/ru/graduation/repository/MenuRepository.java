package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Menu;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    //@Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId ORDER BY m.restaurant.name ASC")
//    List<Menu> findMenuByRestaurantId(int restaurantId);

    //@Query("SELECT m FROM Menu m LEFT JOIN FETCH m.dishes d WHERE m.restaurant.id=:restaurantId ORDER BY m.date DESC, d.name ASC")
    //@Query("SELECT m FROM Menu m JOIN FETCH m.dishes d WHERE m.restaurant.id=:restaurantId ORDER BY m.date DESC, d.name ASC")
    List<Menu> findAllByRestaurantId(int restaurantId);

    Menu findMenuByIdAndRestaurantId(int id, int restaurantId);

    List<Menu> findByRestaurantId(int restaurantId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=?1 AND m.restaurant.id=?2")
    int deleteByIdAndRestaurantId(int id, int restaurantId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=?1")
    int deleteById(int id);
}
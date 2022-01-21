package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Restaurant;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    Restaurant save(Restaurant restaurant);

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=?1")
    int delete(int id);

//    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menu m LEFT OUTER JOIN FETCH m.dishes")
//    List<Restaurant> getAllWithMenuAndDishes();

//    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.menu m JOIN FETCH m.dishes WHERE r.id=?1")
//    Restaurant getAllWithMenuAndDishes(int id);
}
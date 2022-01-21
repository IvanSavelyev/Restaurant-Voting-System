package com.github.ivansavelyev.votingsystem.repository;

import com.github.ivansavelyev.votingsystem.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByRestaurantId(int restaurantId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=?1")
    int delete(int id);

    @Transactional
    Menu save(Menu menu);

    @Query("SELECT m FROM Menu m WHERE m.date=?1 AND m.restaurant.id=?2")
    Menu getMenuByDateAndRestaurantId(LocalDate localDate, int restaurantId);
}
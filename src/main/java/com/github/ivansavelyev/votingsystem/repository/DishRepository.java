package com.github.ivansavelyev.votingsystem.repository;

import com.github.ivansavelyev.votingsystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=?1")
    int deleteById(int id);

    @Transactional
    Dish save(Dish dish);

    @Query("SELECT d FROM Dish d WHERE d.menu.id=?1")
    Set<Dish> findAllByMenuId(int menuId);
}
package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Dish;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("SELECT d FROM Dish d WHERE d.menu.id=?1 ORDER BY d.name ASC")
    List<Dish> findAllByMenuId(int menuId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=?1")
    boolean deleteById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=?1 AND d.menu.id=?2")
    boolean deleteByIdAndMenuId(int id, int menuId);

    @Transactional
    Dish save(Dish dish);

    Dish getByIdAndMenuId(int id, int menuId);
}
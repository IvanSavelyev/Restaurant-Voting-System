package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    Vote save(Vote vote);

    Vote findByUserId(int userId);

    boolean existsByUserId(int userId);

    List<Vote> findAllByVoteDateTime(LocalDateTime localDateTime);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant")
    List<Vote> getAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.user=?1")
    int delete(int userId);
}
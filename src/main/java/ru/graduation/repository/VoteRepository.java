package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    Vote save(Vote vote);

    Vote findByUserId(int userId);

    boolean existsByUserId(int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.voteDate=?1")
    List<Vote> findAllByVoteDate(LocalDate localDate);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote")
    int delete();
}
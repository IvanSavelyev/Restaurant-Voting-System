package com.github.ivansavelyev.votingsystem.repository;

import com.github.ivansavelyev.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    Vote save(Vote vote);

    boolean existsByUserId(int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=?1 AND v.voteDate=?2")
    Vote findUserAndDate(int userId, LocalDate localDate);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.user.id=?1")
    List<Vote> findByUserIdWithRestaurant(int userId);
}
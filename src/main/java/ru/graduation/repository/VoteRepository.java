package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Vote;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    Vote save(Vote vote);

    //    Vote findByUserIdAndVoteDateTime(int userId, LocalDateTime localDateTime);
    Vote findByUserId(int userId);

    boolean existsByUserId(int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.user=?1")
    int delete(int userId);

    @Modifying
    @Transactional
    int deleteByUserId(int id);
}
package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Vote;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    //@Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.voteDateTime <")
    //Vote findByUserIdAndVoteDateTime(int userId, LocalDateTime dateTime);
    Vote findByUserIdAndVoteDateTimeBefore(Integer user_id, @NotNull LocalDateTime voteDateTime);

    Vote findByUserIdAndVoteDateTimeAfter(Integer user_id, @NotNull LocalDateTime voteDateTime);
}
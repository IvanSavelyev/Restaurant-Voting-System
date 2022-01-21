package com.github.ivansavelyev.votingsystem.service;

import com.github.ivansavelyev.votingsystem.TimingExtension;
import com.github.ivansavelyev.votingsystem.model.Vote;
import com.github.ivansavelyev.votingsystem.testdata.RestaurantTestData;
import com.github.ivansavelyev.votingsystem.testdata.VoteTestData;
import com.github.ivansavelyev.votingsystem.util.TimeUtil;
import com.github.ivansavelyev.votingsystem.web.exeption.NotFoundException;
import com.github.ivansavelyev.votingsystem.web.exeption.VoteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.github.ivansavelyev.votingsystem.testdata.UserTestData.*;
import static com.github.ivansavelyev.votingsystem.testdata.VoteTestData.VOTE_MATCHER;
import static com.github.ivansavelyev.votingsystem.testdata.VoteTestData.vote1;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
@ExtendWith(TimingExtension.class)
@ActiveProfiles("test")
public class VoteServiceTest {

    @Autowired
    protected VoteService voteService;

    @Test
    void getNotFoundByUserId() {
        assertThrows(NotFoundException.class, () -> voteService.getByUserIdAndAndVoteDate(USER_NOT_FOUND, LocalDate.now()));
    }

    @Test
    void getByUserIdAndAndVoteDate() {
        VOTE_MATCHER.assertMatch(voteService.getByUserIdAndAndVoteDate(USER_ID, LocalDate.now()), vote1);
    }

    @Test
    void create() {
        Vote created = voteService.create(RestaurantTestData.RESTAURANT_ID, user5.id());
        int newId = created.id();
        Vote newVote = VoteTestData.getNew();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.getByUserIdAndAndVoteDate(user5.id(), LocalDate.now()), newVote);
    }

    @Test
    void createWithException() {
        assertThrows(VoteException.class, () -> voteService.create(RestaurantTestData.RESTAURANT_ID, user1.id()));
    }

    @Test
    void update() {
        if (LocalTime.now().isAfter(TimeUtil.DEAD_LINE_TIME)) {
            assertThrows(VoteException.class, () -> voteService.update(RestaurantTestData.RESTAURANT_ID, user3.id()));
        } else {
            voteService.update(RestaurantTestData.restaurant1.id(), user3.id());
            VOTE_MATCHER.assertMatch(voteService.getByUserIdAndAndVoteDate(user3.id(), LocalDate.now()), VoteTestData.getUpdated());
        }
    }
}

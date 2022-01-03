package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.TimingExtension;
import ru.graduation.model.Vote;
import ru.graduation.testdata.VoteTestData;
import ru.graduation.web.exeption.NotFoundException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static ru.graduation.testdata.UserTestData.*;
import static ru.graduation.testdata.VoteTestData.*;

@SpringBootTest
@Transactional
@ExtendWith(TimingExtension.class)

public class VoteServiceTest {

    @Autowired
    protected VoteService voteService;

    @Test
    void getByUserId() {
        VOTE_MATCHER.assertMatch(voteService.getByUserId(USER_ID), vote1);
    }

    @Test
    void getNotFoundByUserId() {
        assertThrows(NotFoundException.class, () -> voteService.getByUserId(USER_NOT_FOUND));
    }

    @Test
    void getAllByLocalDate() {
        VOTE_MATCHER.assertMatch(voteService.getAllByDate(LocalDate.now()), votes);
    }

    @Test
    void create() {
        Vote created = voteService.create(VoteTestData.getNew());
        int newId = created.id();
        Vote newVote = VoteTestData.getNew();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.getByUserId(user5.id()), newVote);
    }

    @Test
    void update() {
        Vote updated = VoteTestData.getUpdated();
        voteService.update(updated);
        VOTE_MATCHER.assertMatch(voteService.getByUserId(user3.id()), VoteTestData.getUpdated());
    }

    @Test
    void clear() {
        voteService.clear();
        assertThrows(NotFoundException.class, () -> voteService.getByUserId(user.id()));
    }

    @Test
    void checkIfExistByUserId() {
        assertTrue(voteService.checkIfExistByUserId(user.id()));
        assertFalse(voteService.checkIfExistByUserId(USER_NOT_FOUND));
    }
}

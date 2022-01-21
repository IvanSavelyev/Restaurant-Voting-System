package com.github.ivansavelyev.votingsystem.controller;

import com.github.ivansavelyev.votingsystem.TestUtil;
import com.github.ivansavelyev.votingsystem.model.Vote;
import com.github.ivansavelyev.votingsystem.service.VoteService;
import com.github.ivansavelyev.votingsystem.testdata.VoteTestData;
import com.github.ivansavelyev.votingsystem.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.github.ivansavelyev.votingsystem.testdata.UserTestData.*;
import static com.github.ivansavelyev.votingsystem.testdata.VoteTestData.VOTE_MATCHER;
import static com.github.ivansavelyev.votingsystem.testdata.VoteTestData.vote1;
import static com.github.ivansavelyev.votingsystem.util.TimeUtil.DEAD_LINE_TIME;
import static com.github.ivansavelyev.votingsystem.web.controllers.vote.VoteController.VOTE_REST_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/" + VOTE_REST_URL + "/";

    @Autowired
    private VoteService voteService;

    @Test
    public void getByDateAndUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL).param("localDate", "")
                .with(TestUtil.userHttpBasic(user)))
                .andExpect(VOTE_MATCHER.contentJson(vote1));
    }

    @Test
    void create() throws Exception {
        Vote newVote = VoteTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL).param("restaurantId", "1")
                .with(TestUtil.userHttpBasic(user5))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andDo(print())
                .andExpect(status().isCreated());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(voteService.getByUserIdAndAndVoteDate(user5.id(), LocalDate.now()), newVote);
    }

    @Test
    void createMultiply() throws Exception {
        Vote newVote = VoteTestData.getNew();
        perform(MockMvcRequestBuilders.post(REST_URL).param("restaurantId", "1")
                .with(TestUtil.userHttpBasic(user))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void update() throws Exception {
        Vote updated = VoteTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL).param("restaurantId", "3")
                .with(TestUtil.userHttpBasic(user3))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(LocalTime.now().isBefore(DEAD_LINE_TIME) ? status().isNoContent() : status().isConflict());
        VOTE_MATCHER.assertMatch(voteService.getByUserIdAndAndVoteDate(user3.id(), LocalDate.now()),
                LocalTime.now().isBefore(DEAD_LINE_TIME) ? VoteTestData.getUpdated() : VoteTestData.vote5);
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL))
                .andExpect(status().isUnauthorized());
    }
}

package ru.graduation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.graduation.TestUtil;
import ru.graduation.model.Vote;
import ru.graduation.service.VoteService;
import ru.graduation.testdata.VoteTestData;
import ru.graduation.util.TimeUtil;
import ru.graduation.web.exeption.NotFoundException;
import ru.graduation.web.json.JsonUtil;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.testdata.UserTestData.*;
import static ru.graduation.testdata.VoteTestData.VOTE_MATCHER;
import static ru.graduation.testdata.VoteTestData.votes;
import static ru.graduation.web.controllers.vote.VoteController.VOTE_REST_URL;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/" + VOTE_REST_URL + "/";

    @Autowired
    private VoteService voteService;

    @Test
    public void getAllByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL).param("localDate", LocalDate.now().toString())
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(VOTE_MATCHER.contentJson(votes));
    }

    @Test
    public void getAllByDateWithEmptyParam() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(VOTE_MATCHER.contentJson(votes));
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
        VOTE_MATCHER.assertMatch(voteService.getByUserId(user5.id()), newVote);
    }

    @Test
    void createMultiply() throws Exception {
        Vote newVote = VoteTestData.getNew();
        perform(MockMvcRequestBuilders.post(REST_URL).param("restaurantId", "1")
                .with(TestUtil.userHttpBasic(user))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andDo(print())
                .andExpect(status().isLocked());
    }

    //
    @Test
    void update() throws Exception {
        Vote updated = VoteTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL).param("restaurantId", "3")
                .with(TestUtil.userHttpBasic(user3))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(TimeUtil.isBetween(LocalTime.now()) ? status().isNoContent() : status().isForbidden());
        VOTE_MATCHER.assertMatch(voteService.getByUserId(user3.id()),
                TimeUtil.isBetween(LocalTime.now()) ? VoteTestData.getUpdated() : VoteTestData.vote5);
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteFromUser() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "clear")
                .with(TestUtil.userHttpBasic(user3)))
                .andExpect(status().isForbidden());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "clear")
                .with(TestUtil.userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> voteService.getByUserId(user3.id()));
    }

    @Test
    public void getResults() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "results")
                .with(TestUtil.userHttpBasic(user)))
                .andExpect(status().isOk());
    }
}

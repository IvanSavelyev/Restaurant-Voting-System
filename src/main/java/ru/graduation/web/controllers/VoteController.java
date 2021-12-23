package ru.graduation.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Restaurant;
import ru.graduation.model.Vote;
import ru.graduation.service.RestaurantService;
import ru.graduation.service.UserService;
import ru.graduation.service.VoteService;
import ru.graduation.util.SecurityUtil;
import ru.graduation.util.exception.NotChangeYourMindException;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static ru.graduation.web.controllers.VoteController.VOTE_REST_URL;


@RestController
@RequestMapping(value = VOTE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {

    private final LocalTime DEAD_LINE_TIME = LocalTime.of(23, 0, 0);

    public final static String VOTE_REST_URL = "api/votes/";

    private final RestaurantService restaurantService;

    private final UserService userService;

    private final VoteService voteService;

    @PostMapping("restaurants/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    public void vote(@PathVariable int restaurantId) {
        LocalTime currentTime = LocalTime.now();
        Restaurant restaurant = restaurantService.get(restaurantId);
        if (voteService.checkIfExistByUserId(SecurityUtil.authUserId())) {
            Vote vote = voteService.getByUserId(SecurityUtil.authUserId());
            if (currentTime.isBefore(DEAD_LINE_TIME)) {
                vote.setVoteDateTime(LocalDateTime.now());
                vote.setRestaurant(restaurant);
                vote.setUser(userService.get(SecurityUtil.authUserId()));
                voteService.create(vote);
            } else {
                throw new NotChangeYourMindException("You can not change your mind");
            }
        } else {
            voteService.create(new Vote(restaurant, userService.get(SecurityUtil.authUserId())));
        }
    }
//    }
}

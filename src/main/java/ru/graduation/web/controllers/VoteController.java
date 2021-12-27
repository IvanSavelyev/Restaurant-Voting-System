package ru.graduation.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Restaurant;
import ru.graduation.model.Vote;
import ru.graduation.service.RestaurantService;
import ru.graduation.service.UserService;
import ru.graduation.service.VoteService;
import ru.graduation.util.SecurityUtil;
import ru.graduation.util.TimeUtil;
import ru.graduation.util.exception.NotChangeYourMindException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.graduation.web.controllers.VoteController.VOTE_REST_URL;


@RestController
@RequestMapping(value = VOTE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {

    public final static String VOTE_REST_URL = "api/rest/votes";

    private final RestaurantService restaurantService;

    private final UserService userService;

    private final VoteService voteService;

    @GetMapping
    public List<Vote> getAll(){
        return voteService.getAll();
    }

    @PostMapping
    public ResponseEntity<Vote> createVote(@RequestParam int restaurantId) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        Restaurant restaurant = restaurantService.get(restaurantId);
        if (!voteService.checkIfExistByUserId(SecurityUtil.authUserId()) || !TimeUtil.isBetween(nowDateTime.toLocalTime())) {
            return new ResponseEntity<>(voteService.create(new Vote(restaurant, userService.get(SecurityUtil.authUserId()))), HttpStatus.CREATED);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public void updateVote(@RequestParam int restaurantId) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        if (voteService.checkIfExistByUserId(SecurityUtil.authUserId()) && !TimeUtil.isBetween(nowDateTime.toLocalTime())) {
            Vote vote = voteService.getByUserId(SecurityUtil.authUserId());
            vote.setVoteDateTime(nowDateTime);
            vote.setRestaurant(restaurantService.get(restaurantId));
            voteService.create(vote);
        } else {
            throw new NotChangeYourMindException("Too late for change you mind");
        }
    }
}

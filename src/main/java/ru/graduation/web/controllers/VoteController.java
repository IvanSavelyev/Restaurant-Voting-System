package ru.graduation.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Restaurant;
import ru.graduation.model.Vote;
import ru.graduation.service.RestaurantService;
import ru.graduation.service.UserService;
import ru.graduation.service.VoteService;
import ru.graduation.to.VoteTo;
import ru.graduation.util.SecurityUtil;
import ru.graduation.util.TimeUtil;
import ru.graduation.util.VoteUtil;
import ru.graduation.util.exception.NotChangeYourMindException;
import ru.graduation.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Restaurant restaurant = restaurantService.get(restaurantId);
        if (!voteService.checkIfExistByUserId(SecurityUtil.authUserId())) {
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

    @DeleteMapping("/clear")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete() {
        log.debug("Delete all votes");
        voteService.clear();
    }

    @GetMapping("/results")
    public String getResults() {
        log.debug("get voting results");
        List<Vote> votes = voteService.getAll();
        List<VoteTo> voteTos = votes.stream().map(VoteUtil::createTo).toList();
        Map<Object, Long> mapResults;
        mapResults = voteTos.stream().collect(Collectors.groupingBy(VoteTo::getName, Collectors.counting()));
        try {
            return JsonUtil.writeValue(mapResults);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

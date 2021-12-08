package ru.graduation.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Vote;
import ru.graduation.service.VoteService;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static ru.graduation.web.controllers.ProfileVoteController.PROFILE_VOTE_REST_URL;

@RestController
@RequestMapping(value = PROFILE_VOTE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class ProfileVoteController {

    public final static String PROFILE_VOTE_REST_URL = "api/rest/profile/votes/";

    private VoteService voteService;

    @PostMapping(value = "vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> doVote(@RequestParam int restaurantId) {
        Vote created = voteService.vote(restaurantId, 1);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PROFILE_VOTE_REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PatchMapping(value = "vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> change(@RequestParam int restaurantId) {
        Vote updated = voteService.change(restaurantId, 1);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PROFILE_VOTE_REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(updated);
    }
}

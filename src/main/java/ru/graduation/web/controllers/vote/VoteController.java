package ru.graduation.web.controllers.vote;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Vote;
import ru.graduation.service.VoteService;
import ru.graduation.util.SecurityUtil;
import ru.graduation.util.TimeUtil;

import java.time.LocalDate;
import java.util.List;

import static ru.graduation.web.controllers.vote.VoteController.VOTE_REST_URL;


@RestController
@RequestMapping(value = VOTE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Tag(name = "Vote Controller")
public class VoteController {

    public static final String VOTE_REST_URL = "api/rest/votes";

    private final VoteService voteService;

    @GetMapping
    public Vote getByDateAndUserId(@DateTimeFormat(pattern = TimeUtil.DATE_FORMAT_PATTERN)
                                   @RequestParam(value = "localDate",
                                           defaultValue = "#{T(java.time.LocalDate).now().toString()}") LocalDate localDate) {
        return voteService.getByUserIdAndAndVoteDate(SecurityUtil.authId(), localDate);
    }

    @PostMapping
    public ResponseEntity<Vote> create(@RequestParam int restaurantId) {
        return new ResponseEntity<>(voteService.create(restaurantId), HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam int restaurantId) {
        voteService.update(restaurantId);
    }
}

package ru.graduation.web.controllers.vote;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
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
import ru.graduation.web.exeption.MultiplyVoteException;
import ru.graduation.web.exeption.NotChangeYouMindException;
import ru.graduation.web.json.JsonUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.graduation.web.controllers.vote.VoteController.VOTE_REST_URL;


@RestController
@RequestMapping(value = VOTE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Tag(name = "Vote Controller")
public class VoteController {

    public final static String VOTE_REST_URL = "api/rest/votes";

    private final RestaurantService restaurantService;

    private final UserService userService;

    private final VoteService voteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Vote> getAllByDate(@DateTimeFormat(pattern = TimeUtil.DATE_FORMAT_PATTERN) @RequestParam(value = "localDate", defaultValue = "#{T(java.time.LocalDate).now().toString()}") LocalDate localDate) {
        return voteService.getAllByDate(localDate);
    }

    @PostMapping
    public ResponseEntity<Vote> create(@RequestParam int restaurantId) {
        Restaurant restaurant = restaurantService.get(restaurantId);
        if (!voteService.checkIfExistByUserId(SecurityUtil.authId())) {
            return new ResponseEntity<>(voteService.create(new Vote(restaurant, userService.get(SecurityUtil.authId()))), HttpStatus.CREATED);
        } else {
            throw new MultiplyVoteException("You can't vote more then once");
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam int restaurantId) {
        LocalTime nowTime = LocalTime.now();
        if (TimeUtil.isBetween(nowTime)) {
            Vote vote = voteService.getByUserId(SecurityUtil.authId());
            vote.setRestaurant(restaurantService.get(restaurantId));
            voteService.update(vote);
        } else {
            throw new NotChangeYouMindException("Too late to change your mind");
        }
    }

    @DeleteMapping("/clear")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clear() {
        log.debug("Delete all votes");
        voteService.clear();
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUserId(@RequestParam int userId) {
        log.debug("Delete vote from userId: {} ", userId);
        voteService.delete(userId);
    }

    @GetMapping("/results")
    @ResponseStatus(HttpStatus.OK)
    public String getResultsByDate(@DateTimeFormat(pattern = TimeUtil.DATE_FORMAT_PATTERN) @RequestParam(value = "localDate", defaultValue = "#{T(java.time.LocalDate).now().toString()}") LocalDate localDate) {
        log.debug("get voting results by date {}", localDate);
        List<Vote> votes = voteService.getAllByDate(localDate);
        List<VoteTo> voteTos = votes.stream().map(VoteUtil::createTo).toList();
        return JsonUtil.writeValue(voteTos.stream().collect(Collectors.groupingBy(VoteTo::getName, Collectors.counting())));
    }

//    @GetMapping("/total")
//    @ResponseStatus(HttpStatus.OK)
//    public String getResults() {
//        log.debug("get voting results");
//        List<Vote> votes = voteService.getAll();
//        List<VoteTo> voteTos = votes.stream().map(VoteUtil::createTo).toList();
//        return JsonUtil.writeValue(voteTos.stream().collect(Collectors.groupingBy(VoteTo::getName, Collectors.counting())));
//    }
}

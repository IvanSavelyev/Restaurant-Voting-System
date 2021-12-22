package ru.graduation.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.model.Vote;
import ru.graduation.service.RestaurantService;
import ru.graduation.service.UserService;
import ru.graduation.service.VoteService;
import ru.graduation.util.SecurityUtil;

import static ru.graduation.web.controllers.VoteController.VOTE_REST_URL;


@RestController
@RequestMapping(value = VOTE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {

    public final static String VOTE_REST_URL = "api/vote";

    private final RestaurantService restaurantService;
    private final UserService userService;
    private final VoteService voteService;

    public Vote vote(int restaurantId) {
        //Находим юзера
        User user = userService.get(SecurityUtil.authUserId());
        //находим ресторан
        Restaurant restaurant = restaurantService.get(restaurantId);
        //Проверяем раннее существующие голоса юзера
        Vote vote = voteService.getByUserId(SecurityUtil.authUserId());
        if(vote != null){

        }
        return null;
    }
}

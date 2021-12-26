package ru.graduation;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.model.Vote;
import ru.graduation.service.MenuService;
import ru.graduation.service.RestaurantService;
import ru.graduation.service.UserService;
import ru.graduation.service.VoteService;
import ru.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableJpaRepositories
@AllArgsConstructor
public class RestaurantVotingSystemApplication implements ApplicationRunner {
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final VoteService voteService;
    private final MenuService menuService;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingSystemApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println(menuService.getByRestaurantIdWithDish(1));
    }
}

//ADMIN -- GET  RESTAURANTS -> MENU -> DISH
//ADMIN -- PUT/POST -> MENU OF REST
//USER  -- CAN VOTE FOR REST (WITH LIMITS)
//
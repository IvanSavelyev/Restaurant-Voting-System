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

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingSystemApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        final LocalDate currentDate = LocalDate.now();
//        Vote vote = new Vote();
//        //Находим юзера
//        int userId = 1;
//        int restaurantId = 1;
//
//        User user = userService.get(userId);
//        //находим ресторан
//        Restaurant restaurant = restaurantService.get(1);
//        //Проверяем раннее существующие голоса юзера
//        if(voteService.checkIfExistByUserId(userId+100)){
//            System.out.println("user exist");
//        } else {
//            System.out.println("No user are present");
//        }
//        try {
//            vote = voteService.getByUserId(1);
//        } catch (NotFoundException e) {
//            voteService.create(new Vote(LocalDateTime.now(), restaurant, user));
//        }
//        if (vote.getVoteDateTime().toLocalDate().isAfter(currentDate)) {
//            System.out.println("throw NotChangeYourMindException;");
//        } else {
//            vote.setVoteDateTime(LocalDateTime.now());
//            vote.setRestaurant(restaurant);
//            voteService.create(vote);
//        }
    }
}

//ADMIN -- GET  RESTAURANTS -> MENU -> DISH
//ADMIN -- PUT/POST -> MENU OF REST
//USER  -- CAN VOTE FOR REST (WITH LIMITS)
//
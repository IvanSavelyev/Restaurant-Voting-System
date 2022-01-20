package ru.graduation;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.graduation.model.Vote;
import ru.graduation.repository.RestaurantRepository;

@SpringBootApplication
@EnableJpaRepositories
@AllArgsConstructor
public class RestaurantVotingSystemApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingSystemApplication.class, args);
    }

    public RestaurantRepository restaurantRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println(restaurantRepository.getAllWithMenuAndDishes());
//        Vote vote = new Vote();
//        int i = 21;
    }
}

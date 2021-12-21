package ru.graduation;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.graduation.repository.MenuRepository;
import ru.graduation.repository.UserRepository;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaRepositories
@AllArgsConstructor
public class RestaurantVotingSystemApplication implements ApplicationRunner {

//    private final UserRepository userRepository;
//    private final MenuRepository menuRepository;/
//	private final RestaurantRepository restaurantRepository;
//	private final DishRepository dishRepository;
//	private final VoteRepository voteRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingSystemApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//		System.out.println(userRepository.findAll());
//		System.out.println(menuRepository.getWithoutDishes(LocalDate.of(2021, 11, 15), 1));
//        System.out.println(menuRepository.get(LocalDate.of(2021, 5, 15),1));
//		System.out.println(restaurantRepository.findById(1));
//		System.out.println(dishRepository.findById(1).get());


    }
}

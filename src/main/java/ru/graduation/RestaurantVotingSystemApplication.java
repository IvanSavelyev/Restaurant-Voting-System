package ru.graduation;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@AllArgsConstructor
public class RestaurantVotingSystemApplication implements ApplicationRunner {

//	private final UserRepository userRepository;
//	private final MenuRepository menuRepository;
//	private final RestaurantRepository restaurantRepository;
//	private final DishRepository dishRepository;
//	private final VoteRepository voteRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantVotingSystemApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//System.out.println(userRepository.findAll());
//		System.out.println(menuRepository.findMenuByIdAndRestaurantId(1, 1));
//		System.out.println(restaurantRepository.findById(1));
//		System.out.println(dishRepository.findById(1).get());


	}
}

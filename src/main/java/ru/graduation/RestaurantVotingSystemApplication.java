package ru.graduation;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.graduation.repository.UserRepository;

@SpringBootApplication
public class RestaurantVotingSystemApplication implements ApplicationRunner {

	private final UserRepository userRepository;

	public RestaurantVotingSystemApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RestaurantVotingSystemApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//System.out.println(userRepository.findAll());
	}
}

package ru.graduation;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.graduation.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
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

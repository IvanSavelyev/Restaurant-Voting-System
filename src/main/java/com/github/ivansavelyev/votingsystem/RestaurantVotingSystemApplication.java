package com.github.ivansavelyev.votingsystem;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@AllArgsConstructor
@EnableCaching
public class RestaurantVotingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingSystemApplication.class, args);
    }
}

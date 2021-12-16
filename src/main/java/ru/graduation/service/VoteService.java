package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.graduation.model.Vote;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;
import ru.graduation.repository.VoteRepository;
import ru.graduation.util.exception.NotChangeYourMindException;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class VoteService {

    private final LocalTime deadLineTimeToChangeYourMind = LocalTime.of(23, 0);

    private final VoteRepository voteRepository;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    public Vote vote(int restaurantId, int userId) {
        if(voteRepository.findByUserId(userId) != null) {
            return new Vote();
        } else {
            Vote vote = new Vote();
            return voteProcess(vote, restaurantId, userId);
        }
    }

    public Vote change(int restaurantId, int userId) {
        Vote vote = voteRepository.findByUserId(userId);
        if (vote.getVoteDateTime().toLocalTime().isAfter(deadLineTimeToChangeYourMind)) {
            throw new NotChangeYourMindException("You can't change your mind");
        } else {
            return voteProcess(vote, restaurantId, userId);
        }
    }

    private Vote voteProcess(Vote vote, int restaurantId, int userId) {
        vote.setVoteDateTime(LocalDateTime.now());
        vote.setRestaurant(restaurantRepository.getById(restaurantId));
        vote.setUser(userRepository.getById(userId));
        return voteRepository.save(vote);
    }
}

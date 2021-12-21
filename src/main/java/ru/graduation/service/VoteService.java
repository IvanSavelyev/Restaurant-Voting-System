package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.graduation.model.User;
import ru.graduation.model.Vote;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;
import ru.graduation.repository.VoteRepository;

import java.time.LocalTime;

@Service
@AllArgsConstructor
public class VoteService {

//    LocalTime DEAD_LINE_TIME = LocalTime.of(23,00);

    private final UserRepository userRepository;

    private final VoteRepository voteRepository;

    public final RestaurantRepository restaurantRepository;







//    private final LocalTime deadLineTimeToChangeYourMind = LocalTime.of(23, 0);
//
//    private final VoteRepository voteRepository;
//
//    private final UserRepository userRepository;
//
//    private final RestaurantRepository restaurantRepository;
//
//    private final MenuRepository menuRepository;

//    public Vote create(int restaurantId, int userId) {
////        Menu menu = menuRepository.
//    }

///    public Vote change(int restaurantId, int userId) {
//////        Vote vote = voteRepository.findByUserId(userId);
////        if (vote.getVoteDateTime().toLocalTime().isAfter(deadLineTimeToChangeYourMind)) {
////            throw new NotChangeYourMindException("You can't change your mind");
////        } else {
////            return voteProcess(vote, restaurantId, userId);
////        }
//    }

//    private Vote voteProcess(Vote vote, int restaurantId, int userId) {
//        vote.setVoteDateTime(LocalDateTime.now());
//        vote.setRestaurant(restaurantRepository.getById(restaurantId));
//        vote.setUser(userRepository.getById(userId));
//        return voteRepository.save(vote);
//    }
}

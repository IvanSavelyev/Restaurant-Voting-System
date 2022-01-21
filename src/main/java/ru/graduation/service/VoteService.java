package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduation.model.Restaurant;
import ru.graduation.model.Vote;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.VoteRepository;
import ru.graduation.util.SecurityUtil;
import ru.graduation.util.TimeUtil;
import ru.graduation.web.exeption.VoteException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service("VoteService")
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    private final RestaurantRepository restaurantRepository;

    public Vote getByUserIdAndAndVoteDate(int userId, LocalDate localDate) {
        Vote vote = voteRepository.findUserAndDate(userId, localDate);
        Assert.notNull(vote, "vote must not be null");
        return vote;
    }

    @Transactional
    public Vote create(int restaurantId) {
        Vote vote = new Vote();
        if (voteRepository.existsByUserId(SecurityUtil.authId())) {
            vote.setRestaurant(restaurantRepository.getById(restaurantId));
            vote.setUser(SecurityUtil.authUser());
            return voteRepository.save(vote);
        } else {
            throw new VoteException("You can't vote more then once in day");
        }
    }

    @Transactional
    public void update(int restaurantId) {
        Vote vote = voteRepository.getById(SecurityUtil.authId());
        if (voteRepository.existsByUserId(SecurityUtil.authId())) {
            throw new VoteException("You are not voted yet, use post method for voting");
        }
        if (TimeUtil.isBetween(LocalTime.now())) {
            vote.setUser(SecurityUtil.authUser());
            vote.setRestaurant(restaurantRepository.getById(restaurantId));
            voteRepository.save(vote);
        } else {
            throw new VoteException("Too late to change your mind");
        }
    }
}

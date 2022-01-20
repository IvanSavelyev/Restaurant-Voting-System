package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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

    public Vote getByUserId(int userId) {
        return checkNotFoundWithId(voteRepository.findByUserId(userId), userId);
    }

    public List<Vote> getAllByDate(LocalDate localDate) {
        return voteRepository.findAllByVoteDate(localDate);
    }

    @Transactional
    public Vote create(int restaurantId) {
        Vote vote = new Vote();
        Assert.notNull(vote, "vote must not be null");
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
        Vote vote = voteRepository.findByUserId(SecurityUtil.authId());
        Assert.notNull(vote, "vote must not be null");
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

    public void deleteByUserId(int userId) {
        checkNotFoundWithId(voteRepository.deleteByUserId(userId) != 0, userId);
    }
}

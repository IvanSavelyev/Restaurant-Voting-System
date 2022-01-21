package com.github.ivansavelyev.votingsystem.service;

import com.github.ivansavelyev.votingsystem.model.Vote;
import com.github.ivansavelyev.votingsystem.repository.VoteRepository;
import com.github.ivansavelyev.votingsystem.web.exeption.VoteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.github.ivansavelyev.votingsystem.util.TimeUtil.DEAD_LINE_TIME;
import static com.github.ivansavelyev.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    private final RestaurantService restaurantService;

    private final UserService userService;

    public Vote getByUserIdAndAndVoteDate(int userId, LocalDate localDate) {
        return checkNotFoundWithId(voteRepository.findUserAndDate(userId, localDate), userId);
    }

    @Transactional
    public Vote create(int restaurantId, int userId) {
        if (!voteRepository.existsByUserId(userId)) {
            return voteRepository.save(new Vote(restaurantService.get(restaurantId), userService.get(userId)));
        } else {
            throw new VoteException("You can't vote more then once in day");
        }
    }

    @Transactional
    public void update(int restaurantId, int userId) {
        if (voteRepository.existsByUserId(userId)) {
            if (LocalTime.now().isBefore(DEAD_LINE_TIME)) {
                Vote vote = voteRepository.getById(userId);
                vote.setUser(userService.get(userId));
                vote.setRestaurant(restaurantService.get(restaurantId));
                voteRepository.save(vote);
            } else {
                throw new VoteException("Too late to change your mind");
            }
        } else {
            throw new VoteException("Use another method");
        }
    }
}

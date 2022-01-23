package com.github.ivansavelyev.votingsystem.service;

import com.github.ivansavelyev.votingsystem.model.Vote;
import com.github.ivansavelyev.votingsystem.repository.RestaurantRepository;
import com.github.ivansavelyev.votingsystem.repository.UserRepository;
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

    private final RestaurantRepository restaurantRepository;

    private final UserRepository userRepository;

    public Vote getByUserIdAndAndVoteDate(int userId, LocalDate localDate) {
        return checkNotFoundWithId(voteRepository.findUserAndDate(userId, localDate), userId);
    }

    @Transactional
    public Vote create(int restaurantId, int userId) {
        if (!voteRepository.existsByUserId(userId)) {
            return voteRepository.save(new Vote(restaurantRepository.getById(restaurantId), userRepository.getById(userId)));
        } else {
            throw new VoteException("You can't vote more then once in day");
        }
    }

    @Transactional
    public void update(int restaurantId, int userId) {
        if (voteRepository.existsByUserId(userId)) {
            if (LocalTime.now().isBefore(DEAD_LINE_TIME)) {
                Vote vote = voteRepository.findUserAndDate(userId, LocalDate.now());
                vote.setUser(userRepository.getById(userId));
                vote.setRestaurant(restaurantRepository.getById(restaurantId));
                voteRepository.save(vote);
            } else {
                throw new VoteException("Too late to change your mind");
            }
        } else {
            throw new VoteException("Use another method");
        }
    }
}

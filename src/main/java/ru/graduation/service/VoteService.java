package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Vote;
import ru.graduation.repository.VoteRepository;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public Vote getByUserId(int userId) {
        return checkNotFoundWithId(voteRepository.findByUserId(userId), userId);
    }

    //    @CacheEvict(value = "vote", allEntries = true)
    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return voteRepository.save(vote);
    }

    public boolean checkIfExistByUserId(int userId){
        return voteRepository.existsByUserId(userId);
    }
}

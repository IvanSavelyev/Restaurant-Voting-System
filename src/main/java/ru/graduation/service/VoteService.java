package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Restaurant;
import ru.graduation.model.Vote;
import ru.graduation.repository.VoteRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service("VoteService")
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public Vote getByUserId(int userId) {
        return checkNotFoundWithId(voteRepository.findByUserId(userId), userId);
    }

    public List<Vote> getAll(){
        List<Vote> votes = voteRepository.getAll();
        return votes;
    }

    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return voteRepository.save(vote);
    }

    public void update(Vote vote){
        Assert.notNull(vote, "vote must not be null");
        voteRepository.save(vote);
    }

    public boolean checkIfExistByUserId(int userId){
        return voteRepository.existsByUserId(userId);
    }
}

package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduation.model.Vote;
import ru.graduation.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service("VoteService")
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    @Transactional(readOnly = true)
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    public Vote getByUserId(int userId) {
        return checkNotFoundWithId(voteRepository.findByUserId(userId), userId);
    }

    public List<Vote> getAllByDate(LocalDate localDate) {
        return voteRepository.findAllByVoteDate(localDate);
    }

    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return voteRepository.save(vote);
    }

    public void update(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        voteRepository.save(vote);
    }

    public void clear() {
        voteRepository.delete();
    }

    public boolean checkIfExistByUserId(int userId) {
        return voteRepository.existsByUserId(userId);
    }

    public void deleteByUserId(int userId) {
        checkNotFoundWithId(voteRepository.deleteByUserId(userId) != 0, userId);
    }
}

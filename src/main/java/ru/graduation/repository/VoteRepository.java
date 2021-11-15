package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.graduation.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
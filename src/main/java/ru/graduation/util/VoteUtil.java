package ru.graduation.util;

import ru.graduation.model.Vote;
import ru.graduation.to.VoteTo;

public class VoteUtil {

    public static VoteTo createTo(Vote vote){
        return new VoteTo(vote.getRestaurant().getName());
    }
}

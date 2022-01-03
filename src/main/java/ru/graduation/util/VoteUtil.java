package ru.graduation.util;

import lombok.experimental.UtilityClass;
import ru.graduation.model.Vote;
import ru.graduation.to.VoteTo;

@UtilityClass
public class VoteUtil {

    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getRestaurant().getName(), vote.getUser().getId());
    }
}

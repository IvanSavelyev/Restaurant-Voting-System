package com.github.ivansavelyev.votingsystem.testdata;

import com.github.ivansavelyev.votingsystem.MatcherFactory;
import com.github.ivansavelyev.votingsystem.model.Vote;

import java.util.List;

import static com.github.ivansavelyev.votingsystem.testdata.RestaurantTestData.restaurant1;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");

    public static final int NOT_FOUND_VOTE_USER_ID = 5000;
    public static final int VOTE_ID = 1;

    public static final Vote vote1 = new Vote(VOTE_ID);
    public static final Vote vote2 = new Vote(VOTE_ID + 1);
    public static final Vote vote3 = new Vote(VOTE_ID + 2);
    public static final Vote vote4 = new Vote(VOTE_ID + 3);
    public static final Vote vote5 = new Vote(VOTE_ID + 4);
    public static final Vote vote6 = new Vote(VOTE_ID + 5);
    public static final List<Vote> votes = List.of(vote1, vote2, vote3, vote4, vote5, vote6);

    public static Vote getUpdated() {
        return new Vote(vote5.id(), restaurant1, UserTestData.user3);
    }

    public static Vote getNew() {
        return new Vote(restaurant1, UserTestData.user5);
    }
}

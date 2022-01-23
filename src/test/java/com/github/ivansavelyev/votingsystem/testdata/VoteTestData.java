package com.github.ivansavelyev.votingsystem.testdata;

import com.github.ivansavelyev.votingsystem.MatcherFactory;
import com.github.ivansavelyev.votingsystem.model.Vote;

import static com.github.ivansavelyev.votingsystem.testdata.RestaurantTestData.restaurant1;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");

    public static final int VOTE_ID = 1;

    public static final Vote vote1 = new Vote(VOTE_ID);
    public static final Vote vote5 = new Vote(VOTE_ID + 4);

    public static Vote getUpdated() {
        return new Vote(vote5.id(), restaurant1, UserTestData.user3);
    }

    public static Vote getNew() {
        return new Vote(restaurant1, UserTestData.user5);
    }
}

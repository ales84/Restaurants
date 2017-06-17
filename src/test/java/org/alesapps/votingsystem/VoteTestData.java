package org.alesapps.votingsystem;

import org.alesapps.votingsystem.model.BaseEntity;
import org.alesapps.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.alesapps.votingsystem.RestaurantTestData.RESTAURANT1;
import static org.alesapps.votingsystem.RestaurantTestData.RESTAURANT2;
import static org.alesapps.votingsystem.UserTestData.*;

/**
 * Created by Anatoliy Kozhayev on 16.06.2017.
 */
public class VoteTestData {

    public static final int VOTE1_ID = BaseEntity.START_SEQ + 21;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, USER1, RESTAURANT1, LocalDate.of(2017, 6, 8));
    public static final Vote VOTE2 = new Vote(VOTE1_ID + 1, USER2, RESTAURANT2, LocalDate.of(2017, 6, 8));
    public static final Vote VOTE3 = new Vote(VOTE1_ID + 2, ADMIN, RESTAURANT1, LocalDate.of(2017, 6, 8));

    public static final List<Vote> VOTES = Arrays.asList(VOTE1, VOTE2, VOTE3);
}

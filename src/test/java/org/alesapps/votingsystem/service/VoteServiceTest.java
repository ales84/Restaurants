package org.alesapps.votingsystem.service;

import org.alesapps.votingsystem.model.Vote;
import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.alesapps.votingsystem.util.exception.TooLateException;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.*;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.*;
import static org.alesapps.votingsystem.RestaurantTestData.*;
import static org.alesapps.votingsystem.UserTestData.*;
import static org.alesapps.votingsystem.VoteTestData.*;
import static org.junit.Assert.*;

/**
 * Created by Anatoliy Kozhayev on 16.06.2017.
 */
public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void create() throws Exception {
        Vote newVote = new Vote(null, null, null, LocalDate.now());
        Vote created = service.create(newVote, USER1_ID, RESTAURANT1_ID);
        newVote.setId(created.getId());
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(VOTE1, VOTE2, VOTE3, newVote)));
    }

    @Test
    public void duplicateCreate() throws Exception {
        Vote duplicateVote = new Vote(null, null, null, VOTE1.getDate());
        if (LocalTime.now().isBefore(LocalTime.of(11, 0))) {
            service.create(duplicateVote, VOTE1.getUser().getId(), RESTAURANT3.getId());
            assertThat(service.getAll(), is(Matchers.containsInAnyOrder(VOTE1, VOTE2, VOTE3, duplicateVote)));
        } else {
            thrown.expect(TooLateException.class);
            service.create(duplicateVote, VOTE1.getUser().getId(), RESTAURANT3.getId());
        }
    }

    @Test
    public void get() throws Exception {
        Vote actual = service.get(VOTE1_ID);
        assertThat(actual, is(VOTE1));
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getByUserIdAndDate() throws Exception {
        Vote actual = service.getByUserIdAndDate(VOTE2.getUser().getId(), VOTE2.getDate());
        assertThat(actual, is(VOTE2));
    }

    @Test
    public void delete() throws Exception {
        service.delete(VOTE1_ID);
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(VOTE2, VOTE3)));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(1);
    }

    @Test
    public void deleteAll() throws Exception {
        service.deleteAll();
        assertThat(service.getAll(), is(Collections.EMPTY_LIST));
    }

    @Test
    public void getAll() throws Exception {
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(VOTE1, VOTE2, VOTE3)));
    }

    @Test
    public void getAllByDate() throws Exception {
        assertThat(service.getAllByDate(LocalDate.of(2017, 6, 8)),
                is(Matchers.containsInAnyOrder(VOTE1, VOTE2, VOTE3)));
    }
}
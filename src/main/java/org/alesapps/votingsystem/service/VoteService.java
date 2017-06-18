package org.alesapps.votingsystem.service;

import org.alesapps.votingsystem.model.Vote;
import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.alesapps.votingsystem.util.exception.TooLateException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 10.06.2017.
 */
public interface VoteService {

    Vote create(Vote vote, int userId, int restaurantId) throws TooLateException;

    Vote get(int id) throws NotFoundException;

    Vote getByUserIdAndDate(int userId, LocalDate date) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    void deleteAll();

    List<Vote> getAll();

    List<Vote> getAllByDate(LocalDate date);
}

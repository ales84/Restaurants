package org.alesapps.votingsystem.repository;

import org.alesapps.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 10.06.2017.
 */
public interface VoteRepository {

    Vote save(Vote vote, int userId, int restaurantId);

    boolean delete(int id);

    void deleteAll();

    Vote get(int id);

    Vote getByUserIdAndDate(int userId, LocalDate date);

    List<Vote> getAll();

    List<Vote> getAllByDate(LocalDate date);
}

package org.alesapps.votingsystem.service.impl;

import org.alesapps.votingsystem.model.Vote;
import org.alesapps.votingsystem.repository.VoteRepository;
import org.alesapps.votingsystem.service.VoteService;
import org.alesapps.votingsystem.util.ValidationUtil;
import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.alesapps.votingsystem.util.exception.TooLateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 10.06.2017.
 */
@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote create(Vote vote, int userId, int restaurantId) throws TooLateException {
        Assert.notNull(vote, "vote must not be null");
        Vote oldVote = voteRepository.getByUserIdAndDate(userId, vote.getDate());
        if (oldVote == null) {
            return voteRepository.save(vote, userId, restaurantId);
        }
        if (LocalTime.now().isBefore(LocalTime.of(11, 0))) {
            return voteRepository.save(oldVote, userId, restaurantId);
        } else {
            throw new TooLateException("Too late for changed mind");
        }
    }

    @Override
    public Vote get(int id) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(voteRepository.get(id), id);
    }

    @Override
    public Vote getByUserIdAndDate(int userId, LocalDate date) throws NotFoundException {
        return ValidationUtil.checkNotFound(voteRepository.getByUserIdAndDate(userId, date));
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(voteRepository.delete(id), id);
    }

    @Override
    public void deleteAll() {
        voteRepository.deleteAll();
    }

    @Override
    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return voteRepository.getAllByDate(date);
    }
}

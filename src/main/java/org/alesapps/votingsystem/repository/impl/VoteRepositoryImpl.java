package org.alesapps.votingsystem.repository.impl;

import org.alesapps.votingsystem.model.Restaurant;
import org.alesapps.votingsystem.model.User;
import org.alesapps.votingsystem.model.Vote;
import org.alesapps.votingsystem.repository.VoteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 10.06.2017.
 */
@Repository
@Transactional(readOnly = true)
public class VoteRepositoryImpl implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
        vote.setUser(em.getReference(User.class, userId));
        vote.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Vote.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Transactional
    @Override
    public void deleteAll() {
        em.createNamedQuery(Vote.DELETE_ALL).executeUpdate();
    }

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    public Vote getByUserIdAndDate(int userId, LocalDate date) {
        return null;
    }

    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.GET_ALL, Vote.class).getResultList();
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return em.createNamedQuery(Vote.GET_ALL_BY_DATE, Vote.class).getResultList();
    }
}

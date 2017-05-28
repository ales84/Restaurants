package org.alesapps.votingsystem.repository.impl;

import org.alesapps.votingsystem.model.Restaurant;
import org.alesapps.votingsystem.repository.RestaurantRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 22.04.2017.
 */
@Repository
@Transactional(readOnly = true)
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Transactional
    @Override
    public void deleteAll() {
        em.createNamedQuery(Restaurant.DELETE_ALL).executeUpdate();
    }

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.ALL, Restaurant.class).getResultList();
    }
}

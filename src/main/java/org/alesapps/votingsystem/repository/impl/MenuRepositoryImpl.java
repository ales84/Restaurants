package org.alesapps.votingsystem.repository.impl;

import org.alesapps.votingsystem.model.Menu;
import org.alesapps.votingsystem.model.Restaurant;
import org.alesapps.votingsystem.repository.MenuRepository;
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
public class MenuRepositoryImpl implements MenuRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Menu save(Menu menu, int restaurantId) {
        menu.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else {
            return em.merge(menu);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Menu.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Menu get(int id) {
        return em.find(Menu.class, id);
    }

    @Override
    public List<Menu> getAll() {
        return em.createNamedQuery(Menu.ALL, Menu.class).getResultList();
    }
}

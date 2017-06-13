package org.alesapps.votingsystem.repository.impl;

import org.alesapps.votingsystem.model.Dish;
import org.alesapps.votingsystem.model.Menu;
import org.alesapps.votingsystem.repository.DishRepository;
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
public class DishRepositoryImpl implements DishRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Dish save(Dish dish, int menuId) {
        if (!dish.isNew() && get(dish.getId(), menuId) == null) {
            return null;
        }
        dish.setMenu(em.getReference(Menu.class, menuId));
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        } else {
            return em.merge(dish);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id, int menuId) {
        return em.createNamedQuery(Dish.DELETE)
                .setParameter("id", id)
                .setParameter("menuId", menuId)
                .executeUpdate() != 0;
    }

    @Transactional
    @Override
    public void deleteAllByMenuId(int menuId) {
        em.createNamedQuery(Dish.DELETE_ALL_BY_MENU_ID)
                .setParameter("menuId", menuId)
                .executeUpdate();
    }

    @Override
    public Dish get(int id, int menuId) {
        Dish dish = em.find(Dish.class, id);
        return dish != null && dish.getMenu().getId() == menuId ? dish : null;
    }

    @Override
    public List<Dish> getAll() {
        return em.createNamedQuery(Dish.GET_ALL, Dish.class).getResultList();
    }

    @Override
    public List<Dish> getAllByMenuId(int menuId) {
        return em.createNamedQuery(Dish.GET_ALL_BY_MENU_ID, Dish.class)
                .setParameter("menuId", menuId)
                .getResultList();
    }
}

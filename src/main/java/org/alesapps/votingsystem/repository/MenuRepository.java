package org.alesapps.votingsystem.repository;

import org.alesapps.votingsystem.model.Menu;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 19.04.2017.
 */
public interface MenuRepository {

    Menu save(Menu menu, int restaurantId);

    boolean delete(int id);

    void deleteAll();

    Menu get(int id);

    List<Menu> getAll();
}

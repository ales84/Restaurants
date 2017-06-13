package org.alesapps.votingsystem.repository;

import org.alesapps.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 19.04.2017.
 */
public interface MenuRepository {

    Menu save(Menu menu, int restaurantId);

    boolean delete(int id, int restaurantId);

    void deleteAll();

    Menu get(int id, int restaurantId);

    List<Menu> getAll();

    List<Menu> getAllByDate(LocalDate date);

    List<Menu> getAllByDate(LocalDate date, int restaurantId);
}

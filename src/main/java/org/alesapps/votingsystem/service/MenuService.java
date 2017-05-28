package org.alesapps.votingsystem.service;

import org.alesapps.votingsystem.model.Menu;
import org.alesapps.votingsystem.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 23.04.2017.
 */
public interface MenuService {

    Menu create(Menu menu, int restaurantId);

    Menu get(int id) throws NotFoundException;

    Menu update(Menu menu, int restaurantId);

    void delete(int id) throws NotFoundException;

    List<Menu> getAll();
}
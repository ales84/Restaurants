package org.alesapps.votingsystem.service;

import org.alesapps.votingsystem.model.Dish;
import org.alesapps.votingsystem.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 23.04.2017.
 */
public interface DishService {

    Dish create(Dish dish, int menuId);

    Dish get(int id, int menuId) throws NotFoundException;

    Dish update(Dish dish, int menuId);

    void delete(int id, int menuId) throws NotFoundException;

    void deleteAllByMenuId(int menuId);

    List<Dish> getAll();

    List<Dish> getAllByMenuId(int menuId);
}

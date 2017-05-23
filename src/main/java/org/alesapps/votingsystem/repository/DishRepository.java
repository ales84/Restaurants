package org.alesapps.votingsystem.repository;

import org.alesapps.votingsystem.model.Dish;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 19.04.2017.
 */
public interface DishRepository {

    Dish save(Dish dish, int menuId);

    boolean delete(int id, int menuId);

    void deleteAllByMenuId(int menuId);

    Dish get(int id, int menuId);

    List<Dish> getAll();

    List<Dish> getAllByMenuId(int menuId);
}

package org.alesapps.votingsystem.repository;

import org.alesapps.votingsystem.model.Restaurant;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 19.04.2017.
 */
public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    void deleteAll();

    Restaurant get(int id);

    List<Restaurant> getAll();
}

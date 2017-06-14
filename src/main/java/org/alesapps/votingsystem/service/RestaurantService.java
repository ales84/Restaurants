package org.alesapps.votingsystem.service;

import org.alesapps.votingsystem.model.Restaurant;
import org.alesapps.votingsystem.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 23.04.2017.
 */
public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant get(int id) throws NotFoundException;

    Restaurant update(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    void deleteAll();

    List<Restaurant> getAll();

    void evictCache();
}

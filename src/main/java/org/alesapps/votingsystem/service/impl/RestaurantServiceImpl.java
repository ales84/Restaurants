package org.alesapps.votingsystem.service.impl;

import org.alesapps.votingsystem.model.Restaurant;
import org.alesapps.votingsystem.repository.RestaurantRepository;
import org.alesapps.votingsystem.service.RestaurantService;
import org.alesapps.votingsystem.util.ValidationUtil;
import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 26.04.2017.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void deleteAll() {
        restaurantRepository.deleteAll();
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void evictCache() {
        // only for evict cache
    }
}

package org.alesapps.votingsystem.service.impl;

import org.alesapps.votingsystem.model.Dish;
import org.alesapps.votingsystem.repository.DishRepository;
import org.alesapps.votingsystem.service.DishService;
import org.alesapps.votingsystem.util.ValidationUtil;
import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 24.04.2017.
 */
@Service
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish create(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        return dishRepository.save(dish, menuId);
    }

    @Override
    public Dish get(int id, int menuId) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(dishRepository.get(id, menuId), id);
    }

    @Override
    public Dish update(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        return dishRepository.save(dish, menuId);
    }

    @Override
    public void delete(int id, int menuId) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(dishRepository.delete(id, menuId), id);
    }

    @Override
    public void deleteAllByMenuId(int menuId) {
        dishRepository.deleteAllByMenuId(menuId);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.getAll();
    }

    @Override
    public List<Dish> getAllByMenuId(int menuId) {
        return dishRepository.getAllByMenuId(menuId);
    }
}

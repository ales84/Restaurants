package org.alesapps.votingsystem.service.impl;

import org.alesapps.votingsystem.model.Dish;
import org.alesapps.votingsystem.model.Menu;
import org.alesapps.votingsystem.repository.DishRepository;
import org.alesapps.votingsystem.repository.MenuRepository;
import org.alesapps.votingsystem.service.MenuService;
import org.alesapps.votingsystem.util.ValidationUtil;
import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by Anatoliy Kozhayev on 26.04.2017.
 */
@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;

    private DishRepository dishRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository, DishRepository dishRepository) {
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");

        Menu created = menuRepository.save(menu, restaurantId);

        Set<Dish> menuDishes = created.getDishes();
        if (menuDishes != null) {
            menuDishes.forEach(dish -> {
                dish.setId(null);
                dishRepository.save(dish, created.getId());
            });
        }

        return created;
    }

    @Override
    public Menu get(int id, int restaurantId) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(menuRepository.get(id, restaurantId), id);
    }

    @Override
    public Menu update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");

        Set<Dish> oldMenuDishes = get(menu.getId(), restaurantId).getDishes();
        if (oldMenuDishes != null) {
            oldMenuDishes.forEach(dish -> dishRepository.delete(dish.getId(), menu.getId()));
        }

        Set<Dish> newMenuDishes = menu.getDishes();
        if (newMenuDishes != null) {
            newMenuDishes.forEach(dish -> {
                dish.setId(null);
                dishRepository.save(dish, menu.getId());
            });
        }

        return menuRepository.save(menu, restaurantId);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(menuRepository.delete(id, restaurantId), id);
    }

    @Override
    public void deleteAll() {
        menuRepository.deleteAll();
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.getAll();
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date) {
        return menuRepository.getAllByDate(date);
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date, int restaurantId) {
        return menuRepository.getAllByDate(date, restaurantId);
    }
}

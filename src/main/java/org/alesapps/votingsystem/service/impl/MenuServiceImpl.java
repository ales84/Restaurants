package org.alesapps.votingsystem.service.impl;

import org.alesapps.votingsystem.model.Menu;
import org.alesapps.votingsystem.repository.MenuRepository;
import org.alesapps.votingsystem.service.MenuService;
import org.alesapps.votingsystem.util.ValidationUtil;
import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 26.04.2017.
 */
@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return menuRepository.save(menu, restaurantId);
    }

    @Override
    public Menu get(int id) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(menuRepository.get(id), id);
    }

    @Override
    public Menu update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return menuRepository.save(menu, restaurantId);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(menuRepository.delete(id), id);
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.getAll();
    }
}

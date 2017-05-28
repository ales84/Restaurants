package org.alesapps.votingsystem.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.alesapps.votingsystem.json.View;
import org.alesapps.votingsystem.model.Menu;
import org.alesapps.votingsystem.service.DishService;
import org.alesapps.votingsystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.alesapps.votingsystem.web.MenuRestController.REST_URL;

/**
 * Created by Anatoliy Kozhayev on 08.05.2017.
 */
@RestController
@RequestMapping(REST_URL)
public class MenuRestController extends RootController {
    static final String REST_URL = "/api/v1/menus";

    private MenuService menuService;
    private DishService dishService;

    @Autowired
    public MenuRestController(MenuService menuService, DishService dishService) {
        this.menuService = menuService;
        this.dishService = dishService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getMenus() {
        return menuService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu createMenu(@RequestBody Menu menu) {
        menu.setId(null);
        return menuService.create(menu,menu.getRestaurant().getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> updateMenus(@RequestBody List<Menu> menus) {
        menus.forEach(menu -> menuService.update(menu,menu.getRestaurant().getId()));
        return menus;
    }

    @DeleteMapping
    public void deleteMenus() {
        menuService.deleteAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu getMenu(@PathVariable("id") Integer id) {
        return menuService.get(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu updateMenu(@PathVariable("id") Integer id, @RequestBody Menu menu) {
        menu.setId(id);
        return menuService.update(menu,menu.getRestaurant().getId());
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMenu(@PathVariable("id") Integer id) {
        menuService.delete(id);
    }
}

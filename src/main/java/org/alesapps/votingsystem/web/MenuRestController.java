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

/**
 * Created by Anatoliy Kozhayev on 08.05.2017.
 */
@RestController
@RequestMapping("/menus")
public class MenuRestController extends RootController {

    private MenuService menuService;
    private DishService dishService;

    @Autowired
    public MenuRestController(MenuService menuService, DishService dishService) {
        this.menuService = menuService;
        this.dishService = dishService;
    }

    @JsonView(View.Summary.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getMenus() {
        return menuService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu getMenu(@PathVariable("id") Integer id) {
        return menuService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu createMenu(@RequestBody Menu menu) {
        menu.setId(null);
        return menu;/*??????????*/
    }

}

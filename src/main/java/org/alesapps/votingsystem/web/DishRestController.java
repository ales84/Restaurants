package org.alesapps.votingsystem.web;

import org.alesapps.votingsystem.model.Dish;
import org.alesapps.votingsystem.model.Menu;
import org.alesapps.votingsystem.service.DishService;
import org.alesapps.votingsystem.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.alesapps.votingsystem.web.DishRestController.REST_URL;

/**
 * Created by Anatoliy Kozhayev on 08.05.2017.
 */
@RestController
@RequestMapping(REST_URL)
public class DishRestController extends RootController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/api/v1/menus/{menuId}/dishes";

    private DishService dishService;

    @Autowired
    public DishRestController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(@PathVariable Integer menuId) {
        LOG.info("getAll dishes for menu {}", menuId);
        return dishService.getAllByMenuId(menuId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@PathVariable Integer menuId, @RequestBody Dish dish) {
        LOG.info("create {} for menu {}", dish, menuId);
        dish.setId(null);
        Dish created = dishService.create(dish, menuId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{menuId}/dishes/{id}")
                .buildAndExpand(menuId, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable Integer menuId, @PathVariable Integer id) {
        LOG.info("get dish {} for menu {}", id, menuId);
        return dishService.get(id, menuId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Integer menuId, @PathVariable Integer id, @RequestBody Dish dish) {
        LOG.info("update {} with id={} for menu", dish, id, menuId);
        dish.setId(id);
        dishService.update(dish, menuId);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer menuId, @PathVariable Integer id) {
        LOG.info("delete dish {} for menu {}", id, menuId);
        dishService.delete(id, menuId);
    }
}

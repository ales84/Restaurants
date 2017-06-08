package org.alesapps.votingsystem.web;

import org.alesapps.votingsystem.model.Menu;
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

import static org.alesapps.votingsystem.web.RestaurantMenuRestController.REST_URL;

/**
 * Created by Anatoliy Kozhayev on 08.05.2017.
 */
@RestController
@RequestMapping(REST_URL)
public class RestaurantMenuRestController extends RootController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/api/v1/restaurants/{restaurantId}/menus";

    private MenuService menuService;

    @Autowired
    public RestaurantMenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getMenus(@PathVariable Integer restaurantId,
                               @RequestParam(value = "date")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LOG.info("getByDate menus date {} for restaurant {}", date, restaurantId);
        return menuService.getByDate(date, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu, @PathVariable Integer restaurantId) {
        LOG.info("create {} for restaurant {}", menu, restaurantId);
        menu.setId(null);
        menu.setDishes(null);
        Menu created = menuService.create(menu, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu getMenu(@PathVariable Integer restaurantId, @PathVariable Integer id) {
        LOG.info("get menu {} for restaurant {}", id, restaurantId);
        return menuService.get(id, restaurantId);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMenu(@PathVariable Integer restaurantId, @PathVariable Integer id) {
        LOG.info("delete menu {} for restaurant {}", id, restaurantId);
        menuService.delete(id, restaurantId);
    }
}

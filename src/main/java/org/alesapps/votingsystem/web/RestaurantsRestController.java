package org.alesapps.votingsystem.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.alesapps.votingsystem.json.View;
import org.alesapps.votingsystem.model.Restaurant;
import org.alesapps.votingsystem.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.alesapps.votingsystem.web.RestaurantsRestController.REST_URL;

/**
 * Created by Anatoliy Kozhayev on 01.05.2017.
 */
@RestController
@RequestMapping(REST_URL)
public class RestaurantsRestController extends RootController {
    static final String REST_URL = "/api/v1/restaurants";

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantsRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        restaurant.setId(null);
        return restaurantService.create(restaurant);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> updateRestaurants(@RequestBody List<Restaurant> restaurants) {
        restaurants.forEach(restaurant -> restaurantService.update(restaurant));
        return restaurants;
    }

    @DeleteMapping
    public void deleteRestaurants() {
        restaurantService.deleteAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getRestaurant(@PathVariable("id") Integer id) {
        return restaurantService.get(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant updateRestaurant(@PathVariable("id") Integer id, @RequestBody Restaurant restaurant) {
        restaurant.setId(id);
        return restaurantService.update(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRestaurant(@PathVariable("id") Integer id) {
        restaurantService.delete(id);
    }
}

package org.alesapps.votingsystem.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.alesapps.votingsystem.json.View;
import org.alesapps.votingsystem.model.Restaurant;
import org.alesapps.votingsystem.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 01.05.2017.
 */
@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantsRestController extends RootController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantsRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @JsonView(View.Summary.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getRestaurant(@PathVariable("id") Integer id) {
        return restaurantService.get(id);
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

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant updateRestaurant(@PathVariable("id") Integer id, @RequestBody Restaurant restaurant) {
        restaurant.setId(id);
        return restaurantService.update(restaurant);
    }

    @DeleteMapping
    public void deleteRestaurants() {
        restaurantService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public int deleteRestaurant(@PathVariable("id") Integer id) {
        restaurantService.delete(id);
        return id;
    }

/*
    @GetMapping(value = "/{restaurantId}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getRestaurantDishes(@PathVariable("restaurantId") Integer restaurantId) {
        return dishService.getAllByRestaurantId(restaurantId);
    }

    @GetMapping(value = "/{restaurantId}/dishes/{dishId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish getRestaurantDish(@PathVariable("restaurantId") Integer restaurantId, @PathVariable("dishId") Integer dishId) {
        return dishService.get(dishId, restaurantId);
    }

    @PostMapping(value = "/{restaurantId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish createRestaurantDish(@PathVariable("restaurantId") Integer restaurantId, @RequestBody Dish dish) {
        dish.setId(null);
        return dishService.create(dish, restaurantId);
    }

    @PutMapping(value = "/{restaurantId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> updateRestaurantDishes(@PathVariable("restaurantId") Integer restaurantId, @RequestBody List<Dish> dishes) {
        dishes.forEach(dish -> dishService.update(dish, restaurantId));
        return dishes;
    }

    @PutMapping(value = "/{restaurantId}/dishes/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish updateRestaurantDish(@PathVariable("restaurantId") Integer restaurantId,
                                     @PathVariable("dishId") Integer dishId, @RequestBody Dish dish) {
        dish.setId(dishId);
        return dishService.update(dish, restaurantId);
    }

    @DeleteMapping(value = "/{restaurantId}/dishes")
    public void deleteRestaurantDishes(@PathVariable("restaurantId") Integer restaurantId) {
        dishService.deleteAllByRestaurantId(restaurantId);
    }

    @DeleteMapping(value = "/{restaurantId}/dishes/{dishId}")
    public int deleteRestaurantDish(@PathVariable("restaurantId") Integer restaurantId, @PathVariable("dishId") Integer dishId) {
        dishService.delete(dishId, restaurantId);
        return dishId;
    }
*/
}

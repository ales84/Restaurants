package org.alesapps.votingsystem.service;

import org.alesapps.votingsystem.model.Restaurant;
import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.*;
import static org.alesapps.votingsystem.RestaurantTestData.*;
import static org.junit.Assert.*;

/**
 * Created by Anatoliy Kozhayev on 16.06.2017.
 */
public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void create() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "Empire City");
        Restaurant created = service.create(newRestaurant);
        newRestaurant.setId(created.getId());
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(RESTAURANT1, RESTAURANT2, RESTAURANT3, newRestaurant)));
    }

    @Test(expected = DataAccessException.class)
    public void duplicateNameCreate() throws Exception {
        service.create(new Restaurant(null, "Бочонок"));
    }

    @Test
    public void get() throws Exception {
        Restaurant actual = service.get(RESTAURANT1_ID);
        assertThat(actual, is(RESTAURANT1));
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT1.getId(), RESTAURANT1.getName());
        updated.setName("Новый ресторан");
        service.update(updated);
        assertThat(service.get(RESTAURANT1_ID), is(updated));
    }

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT1_ID);
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(RESTAURANT2, RESTAURANT3)));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(1);
    }

    @Test
    public void deleteAll() throws Exception {
        service.deleteAll();
        assertThat(service.getAll(), is(Collections.EMPTY_LIST));
    }

    @Test
    public void getAll() throws Exception {
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(RESTAURANT1, RESTAURANT2, RESTAURANT3)));
    }

}
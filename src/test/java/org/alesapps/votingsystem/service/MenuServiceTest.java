package org.alesapps.votingsystem.service;

import org.alesapps.votingsystem.model.Menu;
import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.*;
import static org.alesapps.votingsystem.DishTestData.*;
import static org.alesapps.votingsystem.MenuTestData.*;
import static org.alesapps.votingsystem.RestaurantTestData.*;
import static org.junit.Assert.*;

/**
 * Created by Anatoliy Kozhayev on 16.06.2017.
 */
public class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void create() throws Exception {
        Menu newMenu = new Menu(null, RESTAURANT3, LocalDate.of(2017, 6, 18), DISHES_MENU3);
        Menu created = service.create(newMenu, RESTAURANT3.getId());
        newMenu.setId(created.getId());
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(MENU1, MENU2, MENU3, MENU4, MENU5, MENU6, newMenu)));
    }

    @Test(expected = DataAccessException.class)
    public void duplicateCreate() throws Exception {
        service.create(new Menu(null, MENU3.getRestaurant(), MENU3.getDate(), DISHES_MENU5), MENU3.getRestaurant().getId());
    }

    @Test
    public void get() throws Exception {
        Menu actual = service.get(MENU1_ID, MENU1.getRestaurant().getId());
        assertThat(actual, is(MENU1));
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(MENU1_ID, MENU2.getRestaurant().getId());
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
        service.delete(MENU1_ID, MENU1.getRestaurant().getId());
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(MENU2, MENU3, MENU4, MENU5, MENU6)));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(MENU1_ID, MENU2.getRestaurant().getId());
    }

    @Test
    public void deleteAll() throws Exception {
        service.deleteAll();
        assertThat(service.getAll(), is(Collections.EMPTY_LIST));
    }

    @Test
    public void getAll() throws Exception {
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(MENU1, MENU2, MENU3, MENU4, MENU5, MENU6)));
    }

    @Test
    public void getAllByDate() throws Exception {
        assertThat(service.getAllByDate(LocalDate.of(2017, 6, 4)),
                is(Matchers.containsInAnyOrder(MENU1, MENU2, MENU3)));
    }

    @Test
    public void getAllByDateAndRestaurantId() throws Exception {
        assertThat(service.getAllByDateAndRestaurantId(
                LocalDate.of(2017, 6, 4), MENU2.getRestaurant().getId()),
                is(Collections.singletonList(MENU2)));
    }
}
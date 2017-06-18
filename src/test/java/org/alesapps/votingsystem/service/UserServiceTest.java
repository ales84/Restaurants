package org.alesapps.votingsystem.service;

import org.alesapps.votingsystem.model.Role;
import org.alesapps.votingsystem.model.User;
import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.*;
import static org.alesapps.votingsystem.UserTestData.*;
import static org.junit.Assert.*;

/**
 * Created by Anatoliy Kozhayev on 16.06.2017.
 */
public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "new", "password", Role.ROLE_USER);
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(USER1, USER2, ADMIN, newUser)));
    }

    @Test(expected = DataAccessException.class)
    public void duplicateNameCreate() throws Exception {
        service.create(new User(null, "user1", "password", Role.ROLE_USER));
    }

    @Test
    public void get() throws Exception {
        User actual = service.get(ADMIN_ID);
        assertThat(actual, is(ADMIN));
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1000);
    }

    @Test
    public void getByName() throws Exception {
        User actual = service.getByName("user2");
        assertThat(actual, is(USER2));
    }

    @Test
    public void update() throws Exception {
        User updated = new User(ADMIN_ID, ADMIN.getName(), ADMIN.getPassword(), ADMIN.getRoles());
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singleton(Role.ROLE_USER));
        service.update(updated);
        assertThat(service.get(ADMIN_ID), is(updated));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER2_ID);
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(USER1, ADMIN)));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(1000);
    }

    @Test
    public void getAll() throws Exception {
        assertThat(service.getAll(), is(Matchers.containsInAnyOrder(USER1, USER2, ADMIN)));
    }
}
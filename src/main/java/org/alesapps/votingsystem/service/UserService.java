package org.alesapps.votingsystem.service;

import org.alesapps.votingsystem.model.User;
import org.alesapps.votingsystem.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 23.04.2017.
 */
public interface UserService {

    User create(User user);

    User get(int id) throws NotFoundException;

    void update(User user);

    void delete(int id) throws NotFoundException;

    List<User> getAll();
}

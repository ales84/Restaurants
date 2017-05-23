package org.alesapps.votingsystem.repository;

import org.alesapps.votingsystem.model.User;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 19.04.2017.
 */
public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    List<User> getAll();
}

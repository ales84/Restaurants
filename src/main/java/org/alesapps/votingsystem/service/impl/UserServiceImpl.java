package org.alesapps.votingsystem.service.impl;

import org.alesapps.votingsystem.AuthorizedUser;
import org.alesapps.votingsystem.model.User;
import org.alesapps.votingsystem.repository.UserRepository;
import org.alesapps.votingsystem.service.UserService;
import org.alesapps.votingsystem.to.UserTo;
import org.alesapps.votingsystem.util.UserUtil;
import org.alesapps.votingsystem.util.ValidationUtil;
import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 26.04.2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByName(username.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }
        return new AuthorizedUser(user);
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(UserUtil.prepareToSave(user));
    }

    @Override
    public User get(int id) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(userRepository.get(id), id);
    }

    @Override
    public User getByName(String name) throws NotFoundException {
        Assert.notNull(name, "name must not be null");
        return ValidationUtil.checkNotFound(userRepository.getByName(name), "name=" + name);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        userRepository.save(UserUtil.prepareToSave(user));
    }

    @Override
    public void update(UserTo userTo) {
        User user = UserUtil.fromTo(userTo);
        userRepository.save(UserUtil.prepareToSave(user));
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }
}

package org.alesapps.votingsystem.web;

import org.alesapps.votingsystem.AuthorizedUser;
import org.alesapps.votingsystem.model.User;
import org.alesapps.votingsystem.service.UserService;
import org.alesapps.votingsystem.to.UserTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.alesapps.votingsystem.web.ProfileRestController.REST_URL;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Anatoliy Kozhayev on 12.06.2017.
 */
@RestController
@RequestMapping(REST_URL)
public class ProfileRestController extends RootController {
    private static final Logger LOG = getLogger(ProfileRestController.class);

    static final String REST_URL = "/api/v1/profile";

    private UserService userService;

    @Autowired
    public ProfileRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        LOG.info("get user {}", authorizedUser.getId());
        return userService.get(authorizedUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        userTo.setId(authorizedUser.getId());
        LOG.info("update {} with id={}", userTo, authorizedUser.getId());
        userService.update(userTo);
    }

    @DeleteMapping()
    public void delete(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        LOG.info("delete user {}", authorizedUser.getId());
        userService.delete(authorizedUser.getId());
    }
}

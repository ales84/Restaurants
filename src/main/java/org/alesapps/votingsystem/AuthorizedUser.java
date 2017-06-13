package org.alesapps.votingsystem;

import org.alesapps.votingsystem.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * Created by Anatoliy Kozhayev on 10.06.2017.
 */
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getName(), user.getPassword(), true, true, true, true, user.getRoles());
        this.user = user;
    }

    private static AuthorizedUser safeGet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        Objects.requireNonNull(user, "No authorized user found");
        return user;
    }

    public int getId() {
        return user.getId();
    }

    public static int id() {
        return get().user.getId();
    }
}

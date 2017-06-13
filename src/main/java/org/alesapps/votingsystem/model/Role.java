package org.alesapps.votingsystem.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Anatoliy Kozhayev on 17.04.2017.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

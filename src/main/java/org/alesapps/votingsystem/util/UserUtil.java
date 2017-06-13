package org.alesapps.votingsystem.util;

import org.alesapps.votingsystem.model.Role;
import org.alesapps.votingsystem.model.User;
import org.alesapps.votingsystem.to.UserTo;

/**
 * Created by Anatoliy Kozhayev on 12.06.2017.
 */
public class UserUtil {

    public static User fromTo(UserTo userTo) {
        return new User(userTo.getId(), userTo.getName(), userTo.getPassword(), Role.ROLE_USER);
    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setName(user.getName().toLowerCase());
        return user;
    }
}

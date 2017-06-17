package org.alesapps.votingsystem;

import org.alesapps.votingsystem.model.BaseEntity;
import org.alesapps.votingsystem.model.Role;
import org.alesapps.votingsystem.model.User;

/**
 * Created by Anatoliy Kozhayev on 16.06.2017.
 */
public class UserTestData {

    public static final int USER1_ID = BaseEntity.START_SEQ;
    public static final int USER2_ID = BaseEntity.START_SEQ + 1;
    public static final int ADMIN_ID = BaseEntity.START_SEQ + 2;

    public static final User USER1 = new User(USER1_ID, "user1", "pass1", Role.ROLE_USER);
    public static final User USER2 = new User(USER2_ID, "user2", "pass2", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin", Role.ROLE_ADMIN);
}

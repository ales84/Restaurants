package org.alesapps.votingsystem.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * Created by Anatoliy Kozhayev on 12.06.2017.
 */
public class PasswordUtil {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static PasswordEncoder getPasswordEncoder() {
        return PASSWORD_ENCODER;
    }

    public static String encode(String rawPassword) {
        if (!StringUtils.hasText(rawPassword)) {
            return null;
        }
        return PASSWORD_ENCODER.encode(rawPassword);
    }

    public static boolean isMatch(String rawPassword, String password) {
        return PASSWORD_ENCODER.matches(rawPassword, password);
    }
}

package org.alesapps.votingsystem.util.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Anatoliy Kozhayev on 12.06.2017.
 */
public class TooLateException extends ApplicationException {
    public static final String EXCEPTION_TOO_LATE = "exception.tooLate";

    public TooLateException(String arg) {
        super(EXCEPTION_TOO_LATE, HttpStatus.UNPROCESSABLE_ENTITY, arg);
    }
}

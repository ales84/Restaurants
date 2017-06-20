package org.alesapps.votingsystem.util.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Anatoliy Kozhayev on 23.04.2017.
 */
public class NotFoundException extends ApplicationException {
    private static final String EXCEPTION_NOT_FOUND = "exception.notFound";

    public NotFoundException(String arg) {
        super(EXCEPTION_NOT_FOUND, HttpStatus.UNPROCESSABLE_ENTITY, arg);
    }
}

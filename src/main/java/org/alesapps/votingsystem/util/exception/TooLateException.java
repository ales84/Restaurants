package org.alesapps.votingsystem.util.exception;

/**
 * Created by Anatoliy Kozhayev on 12.06.2017.
 */
public class TooLateException extends RuntimeException {

    public TooLateException(String message) {
        super(message);
    }
}

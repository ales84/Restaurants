package org.alesapps.votingsystem.util.exception;

/**
 * Created by Anatoliy Kozhayev on 19.06.2017.
 */
public class ErrorInfo {
    private final String url;
    private final String cause;
    private final String[] details;

    public ErrorInfo(CharSequence url, String cause, String... details) {
        this.url = url.toString();
        this.cause = cause;
        this.details = details;
    }

    public ErrorInfo(CharSequence url, Throwable ex) {
        this(url, ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }
}

package org.alesapps.votingsystem.web;

import org.alesapps.votingsystem.util.ValidationUtil;
import org.alesapps.votingsystem.util.exception.ApplicationException;
import org.alesapps.votingsystem.util.exception.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by Anatoliy Kozhayev on 19.06.2017.
 */
@ControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionInfoHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorInfo conflict(HttpServletRequest request, DataIntegrityViolationException e) {
        return logAndGetErrorInfo(request, e);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ErrorInfo bindValidationError(HttpServletRequest request, BindingResult result) {
        return logAndGetValidationErrorInfo(request, result);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorInfo restValidationError(HttpServletRequest request, MethodArgumentNotValidException e) {
        return logAndGetValidationErrorInfo(request, e.getBindingResult());
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfo> applicationError(HttpServletRequest request, ApplicationException e) {
        return getErrorInfoResponseEntity(request, e, e.getMsgCode(), e.getHttpStatus(), e.getArgs());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleError(HttpServletRequest request, Exception e) {
        return logAndGetErrorInfo(request, e);
    }

    private ErrorInfo logAndGetValidationErrorInfo(HttpServletRequest request, BindingResult result) {
        String[] details = result.getAllErrors().stream()
                .map(fe -> messageSource.getMessage(fe, LocaleContextHolder.getLocale()))
                .toArray(String[]::new);
        LOG.warn("{} exception at request {}: {}", "ValidationException", request.getRequestURL(), Arrays.toString(details));
        return new ErrorInfo(request.getRequestURL(), "ValidationException", details);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest request, Exception e) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        LOG.warn("Exception at request {}" + request.getRequestURL(), rootCause);
        return new ErrorInfo(request.getRequestURL(), rootCause);
    }

    private ResponseEntity<ErrorInfo> getErrorInfoResponseEntity(HttpServletRequest request, Exception e,
                                                                 String msgCode, HttpStatus httpStatus, String... args) {
        LOG.warn("Application error: {}", ValidationUtil.getRootCause(e).toString());
        ErrorInfo errorInfo = new ErrorInfo(request.getRequestURL(),
                ValidationUtil.getRootCause(e).getClass().getSimpleName(),
                messageSource.getMessage(msgCode, args, LocaleContextHolder.getLocale()));
        return new ResponseEntity<>(errorInfo, httpStatus);
    }
}

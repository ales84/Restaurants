package org.alesapps.votingsystem.web;

import org.alesapps.votingsystem.util.exception.NotFoundException;
import org.alesapps.votingsystem.util.exception.TooLateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anatoliy Kozhayev on 03.05.2017.
 */
@Controller
public class RootController {

/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }
*/

/*
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TooLateException.class)
    public String handleTooLateException(TooLateException ex) {
        return ex.getMessage();
    }
*/
}

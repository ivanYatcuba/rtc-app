package net.github.rtc.app.controller.common;

import net.github.rtc.app.exception.ServiceProcessingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Amira on 08.10.2014.
 */

@ControllerAdvice
public class ExceptionController {

    private static final String ERROR_PAGE = "error/error";
    private static final String ERROR_MSG = "errorMessage";
    private static final String ERROR_ADV_MSG = "errorAdvancedMessage";

    @ExceptionHandler(ServiceProcessingException.class)
    public ModelAndView handleCustomException(ServiceProcessingException e) {
        final ModelAndView mnv = new ModelAndView(ERROR_PAGE);
        mnv.addObject(ERROR_MSG, e.getMessage());
        mnv.addObject(ERROR_ADV_MSG, getErrorAdvancedMessage(e));
        return mnv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception e) {
        final ModelAndView mnv = new ModelAndView(ERROR_PAGE);
        mnv.addObject(ERROR_MSG, e.getMessage());
        mnv.addObject(ERROR_ADV_MSG, getErrorAdvancedMessage(e));
        return mnv;
    }

    private String getErrorAdvancedMessage(Exception e) {
        String st = "\nERROR DETAILS\n";
        st += "\nWHERE:   " + e.getStackTrace()[0];
        st += "\nTHROWN:  " + e.getClass().getName();
        return st;
    }
}

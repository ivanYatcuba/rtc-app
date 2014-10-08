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

    @ExceptionHandler(ServiceProcessingException.class)
    public ModelAndView handleCustomException(ServiceProcessingException e) {
        final ModelAndView mnv = new ModelAndView("error/error");
        mnv.addObject("errorMessage", e.getMessage());
        return mnv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception e) {
        final ModelAndView mnv = new ModelAndView("error/error");
        mnv.addObject("errorMessage", e.getMessage());
        return mnv;
    }

}

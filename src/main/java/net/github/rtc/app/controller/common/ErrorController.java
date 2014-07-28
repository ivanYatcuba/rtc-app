package net.github.rtc.app.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Ivan Yatcuba on 7/24/14.
 */
@Controller
public class ErrorController {

    @RequestMapping("error404")
    public ModelAndView redirectToErrorPage() {
        return new ModelAndView("error/error");
    }



}

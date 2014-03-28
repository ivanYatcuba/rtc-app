package rtc.app.controllers.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author  Lapshin Ugene
 */

@Controller ("adminNavigationController")
@RequestMapping ("admin/user")
public class AdminNavigationController {

    @RequestMapping (value = "/UserPage", method = RequestMethod.GET)
    public String register(ModelMap model) {
        return "UserPage";
    }



}

package rtc.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

   // @Autowired
   // UserService userService;


    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView register() {
        //TODO
        return null;
    }

    @RequestMapping(value="/userView", method = RequestMethod.GET)
    public ModelAndView userView() {
        //TODO
        return null;
    }

    @RequestMapping(value="/userEdit", method = RequestMethod.GET)
    public ModelAndView userEdit() {
        //TODO
        return null;
    }

}

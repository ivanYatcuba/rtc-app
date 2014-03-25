package rtc.app.controllers;

import com.springapp.mvc.Login;
import com.springapp.mvc.Project;
import net.github.rtc.web.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.util.rtc.validation.Validation;

import java.util.ArrayList;

import java.util.List;
import net.github.rtc.web.user.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PageController {
    private static List<Login> userLogin = new ArrayList<Login>();
    private static List<Project> project = new ArrayList<Project>();
    private List<User> users = new ArrayList<User>();
    private User fictUser = new User();

    @Autowired
    Validation validation;

    static {
        userLogin.add(new Login("Lisitsa", "1234"));
        userLogin.add(new Login("Bogdan", "1234"));
        userLogin.add(new Login("Yarosav", "1234"));
    }

    static {
        project.add(new Project("Project 1"));
        project.add(new Project("Project 2"));
        project.add(new Project("Project 3"));
        project.add(new Project("Project 4"));
        project.add(new Project("Project 5"));
    }


    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }
    
    
    
    @RequestMapping(value = "/get_user/{Id}", method = RequestMethod.GET)
    public ModelAndView single(@PathVariable Integer Id) {
        ModelAndView mav = new ModelAndView("user/user");
       // service.create(new User(2, "Alex", "phone", "scorp", "dnepr", "uni", "fac", "1234"));
        User user = service.findById(Id);
        mav.addObject("user", user);
        return mav;
    }
//    @RequestMapping(value = "/w", method = RequestMethod.POST)
//    public ModelAndView create(BindingResult bindingResult,
//                         SessionStatus session) {
//      ModelAndView mav = new ModelAndView("user/user");
//      User u;
//       u = service.create(new User(2, "Alex", "phone", "scorp", "dnepr", "uni", "fac", "1234"));
//        session.setComplete();
//        mav.addObject("user", u);
//         return mav;
//    }
    

    @RequestMapping(value = "/start input", method = RequestMethod.GET)
    public String Home(ModelMap model) {

        return "in";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String register(ModelMap model) {
        validation.fromClassToJSON(User.class, LocaleContextHolder.getLocale());
        String rules = validation.getJSON(User.class);
        model.addAttribute("validation", rules);
        return "homepage";
    }

    //        @RequestMapping(value="/registration",method=RequestMethod.POST)
//        public String registration(ModelMap model)
//        {
//            return "registration";
//        }

    @RequestMapping(value = "/goHome", method = RequestMethod.GET)
    public String goHome(ModelMap model) {
        validation.fromClassToJSON(User.class, LocaleContextHolder.getLocale());
        String rules = validation.getJSON(User.class);
        model.addAttribute("validation", rules);
        return "homepage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationLang(@ModelAttribute("userForm") User user1, ModelMap model) {

        //this.users.add(user1);

        return "registration";
    }

    @RequestMapping(value = "/viewPage", method = RequestMethod.POST)
    public String viewPage(@ModelAttribute("userForm") User user, ModelMap model,BindingResult bindingResult,
                         SessionStatus session) {


//        System.out.println("fictUser.getFirstname()" + fictUser.getFirstname());
//        System.out.println("fictUser.getLastname()" + fictUser.getLastname());
        this.users.add(user);

        service.create(user);
        session.setComplete();
        model.addAttribute("project", project);
        model.addAttribute("user1", user);
        model.addAttribute("user", userLogin);
        return "viewPage";
    }

    @RequestMapping(value = "/viewPage", method = RequestMethod.GET)
    public String viewPageLang(ModelMap model) {


        return "viewPage";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPage(ModelMap model) {
        model.addAttribute("user", fictUser);
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPageLang(ModelMap model) {
        return "edit";
    }

    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public String input(@ModelAttribute("Logins") Login login, ModelMap model) {
        for (User l : users) {
            if ((login.getLogin().equals(l.getEmail()) == true) && (login.getPassword().equals(l.getPassword()) == true)) {

                model.addAttribute("project", project);
                model.addAttribute("user1", l);
                model.addAttribute("user", userLogin);
                return "viewPage";


            } else {
                model.addAttribute("message", "login and password invalid!");
            }
        }

        model.addAttribute("login", login.getLogin());
        model.addAttribute("password", login.getPassword());
        return "in";
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String inputlan(ModelMap model) {
        return "in";
    }

    @RequestMapping(value = "/input1", method = RequestMethod.GET)
    public String inputlan12(ModelMap model) {
        model.addAttribute("user", userLogin);
        model.addAttribute("project", project);
        //User user1 = new User("lisitsa", "0967309619", "scorpter@mail.ru", "Dnepropetrovsk", "DNUZT", "Teh kibern", "1234");
        model.addAttribute("user1", fictUser);
        return "registration";
    }
}

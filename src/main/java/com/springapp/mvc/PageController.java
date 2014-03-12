package com.springapp.mvc;

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

@Controller
@RequestMapping("/")
public class PageController {
private static List<Login> userLogin = new ArrayList<Login>();
private static List<Project> project = new ArrayList<Project>();

private User fictUser = new User();

    @Autowired
    Validation validation;

static {
    userLogin.add(new Login("Lisitsa","1234"));
    userLogin.add(new Login("Bogdan","1234"));
    userLogin.add(new Login("Yarosav","1234"));
}
static {
    project.add(new Project("Project 1"));
    project.add(new Project("Project 2"));
    project.add(new Project("Project 3"));
    project.add(new Project("Project 4"));
    project.add(new Project("Project 5"));
}
    @RequestMapping(value="/start input",method = RequestMethod.GET)
    public String Home( ModelMap model) {

        return "in";
    }

	@RequestMapping(value="/",method = RequestMethod.GET)
     public String register( ModelMap model) {
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
     @RequestMapping(value="/goHome",method = RequestMethod.GET)
     public String goHome( ModelMap model) {

        return "homepage";
    }
        
	@RequestMapping(value="/registration",method=RequestMethod.GET)
        public String registrationLang(ModelMap model)
        {
            User user = new User();
            return "registration";
        }
        
    @RequestMapping(value="/viewPage",method=RequestMethod.POST)
    public String viewPage(@ModelAttribute("userForm") User user, ModelMap model)
    {
        fictUser = user;
//        System.out.println("fictUser.getFirstname()" + fictUser.getFirstname());
//        System.out.println("fictUser.getLastname()" + fictUser.getLastname());
        return "viewPage";
    }
    @RequestMapping(value="/viewPage",method=RequestMethod.GET)
    public String viewPageLang(ModelMap model)
    {
        
        
        return "viewPage";
    }
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public String editPage(ModelMap model)
    {
        model.addAttribute("user",fictUser);
        return "edit";
    }
    
    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String editPageLang(ModelMap model)
    {
        return "edit";
    }

    @RequestMapping(value="/input",method=RequestMethod.POST)
    public String input(@ModelAttribute("Logins") Login login, ModelMap model)
    {
        for(Login l: userLogin)
        {
            if((login.getLogin().equals(l.getLogin())==true)&& (login.getPassword().equals(l.getPassword())==true)){
                
                    model.addAttribute("message", "login and password is correct!");
                    break;
            }
             else 
        {
                 model.addAttribute("message", "login and password invalid!");
                }
            }
            
        model.addAttribute("login", login.getLogin());
        model.addAttribute("password",login.getPassword());
        return "in";
    }
    @RequestMapping(value="/input",method=RequestMethod.GET)
    public String inputlan(ModelMap model)
    {
        return "in";
    }
    @RequestMapping(value="/input1",method=RequestMethod.GET)
    public String inputlan12(ModelMap model)
    {
        model.addAttribute("user",userLogin);
        model.addAttribute("project",project);
        return "viewPage";
    }
}

package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class PageController {
private static List<Login> userLogin = new ArrayList<Login>();


User fictUser = new User();


static {
    userLogin.add(new Login("Lisitsa","1234"));
    userLogin.add(new Login("Bogdan","1234"));
    userLogin.add(new Login("Yarosav","1234"));
}
    @RequestMapping(value="/start input",method = RequestMethod.GET)
    public String Home( ModelMap model) {

        return "in";
    }

	@RequestMapping(value="/",method = RequestMethod.GET)
     public String register( ModelMap model) {

        return "homepage";
    }

        @RequestMapping(value="/registration",method=RequestMethod.POST)
        public String registration(ModelMap model)
        {
            return "registration";
        }

    @RequestMapping(value="/viewPage",method=RequestMethod.POST)
    public String viewPage(ModelMap model)
    {
        return "viewPage";
    }
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public String editPage(ModelMap model)
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
}

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





static {userLogin.add(new Login("Lisitsa","1234"));
    userLogin.add(new Login("Bogdan","1234"));
    userLogin.add(new Login("Yarosav","1234"));
}
//    private static List<User> userList = new ArrayList<User>();
//
//    static{
//        userList.add(new User("Ivanov Ivan Ivanovych","42-43-44", "iv@i.ua", 1991) );
//        userList.add(new User("Mykolov Mykola Mykolayovych","12-13-14", "my@i.ua", 1986) );
//        userList.add(new User("Vasyliv Vasyl Vasylyovych","32-33-34", "vs@i.ua", 1994) );
//        userList.add(new User("Petrenko Petro Petrovych","42-43-44", "iv@i.ua", 1991) );
//    }

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String register( ModelMap model) {
		
		return "in";
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
    
}

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
public class HelloController {

//    private static List<User> userList = new ArrayList<User>();
//
//    static{
//        userList.add(new User("Ivanov Ivan Ivanovych","42-43-44", "iv@i.ua", 1991) );
//        userList.add(new User("Mykolov Mykola Mykolayovych","12-13-14", "my@i.ua", 1986) );
//        userList.add(new User("Vasyliv Vasyl Vasylyovych","32-33-34", "vs@i.ua", 1994) );
//        userList.add(new User("Petrenko Petro Petrovych","42-43-44", "iv@i.ua", 1991) );
//    }

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String register(ModelMap model) {
		model.addAttribute("message", "Hello worl1234567890-d!");
	 //System.out.println("gfhdgbdfg");
		return "in";
	}
        
        @RequestMapping(value="/registration",method=RequestMethod.POST)
        public String registration(ModelMap model)
        {
         //   System.out.println("gfhdgbdfg");
            return "registration";
        }

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String add(@ModelAttribute("user") User user) {
//
//        if (null != user && null != user.getFio()
//                 && !user.getFio().isEmpty()
//                && null != user.getEmail()
//                && !user.getEmail().isEmpty()
//                && null != user.getPhone()
//                && !user.getPhone().isEmpty()) {
//
//            synchronized (userList) {
//                userList.add(user);
//            }
//        }
//        return "redirect:registration.html";
//    }
}
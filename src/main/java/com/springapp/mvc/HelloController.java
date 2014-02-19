package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
<<<<<<< HEAD
		model.addAttribute("message", "Hello worl1234567890-d!");
		return "in";
=======
		model.addAttribute("message", "Hello, world!!!!");
		return "hello";
>>>>>>> e8c781c628df708d01d73bf9a52792bfa9476231
	}
}

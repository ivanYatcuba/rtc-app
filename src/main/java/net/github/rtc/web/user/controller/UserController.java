package net.github.rtc.web.user.controller;

import java.util.Collection;
import net.github.rtc.web.user.model.User;
import net.github.rtc.web.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author  Lapshin Ugene
 */


@Controller ("adminNavigationController")
@RequestMapping ("admin/User")
public class UserController {

    private static final String ROOT = "admin";
  //  private static final String ROOT2 = "admin/user";
   private UserService userService;
     @Autowired
    public void setService(UserService service) {
        this.userService = service;
    }
Collection <User> listUser;

  /*  @RequestMapping (value = "/qwe", method = RequestMethod.GET)
    public ModelAndView single(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        User user = userService.findById(id);
        //mav.addObject("user", user);
        mav.addObject("content", "ViewAll");
        return mav;
       // return "ViewAll";
    }
*/
    @RequestMapping(value = "/ViewAll", method = RequestMethod.GET)
    public ModelAndView View() {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.addObject("content", "User/content/ViewAll");
        listUser=userService.findAll();
        mav.addObject("users", listUser);
        return mav;
    }

    @RequestMapping(value = "/EditPage", method = RequestMethod.GET)
    public ModelAndView EditPage() {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.addObject("content", "User/content/EditPage");
        return mav;
    }
    @RequestMapping(value = "/UserPage", method = RequestMethod.GET)
    public ModelAndView UserPage() {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.addObject("content", "User/content/UserPage");
        return mav;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id,ModelMap model) {
        userService.delete(id);
        listUser=userService.findAll();
        model.addAttribute("users", listUser);
        model.addAttribute("content", "User/content/ViewAll");
        return  ROOT + "/layout";
    }

}

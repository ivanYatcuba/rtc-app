package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.Role;
import net.github.rtc.app.model.Roles;
import net.github.rtc.app.model.User;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.propertyeditors.CustomTagsEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

//import net.github.rtc.app.courses.model.SearchFilter;
//import net.github.rtc.app.service.UserServiceLogin;
//import org.springframework.ui.ModelMap;

/**
 * @author  Lapshin Ugene
 */


@Controller ("adminNavigationController")
@RequestMapping ("admin/user")
public class UserController {
    @Autowired
    private ValidationContext validationContext;

    private static final String ROOT = "admin";
    private static final String ROOT_MODEL = "user";
    private UserService userService;
     @Autowired
    public void setService(UserService service) {
        this.userService = service;
    }

    @RequestMapping(value = "/viewAll", method = RequestMethod.GET)
    public ModelAndView viewAll() {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.addObject("content", "user/content/viewAll");
        Collection<User> listUser=userService.findAll();
        mav.addObject("users", listUser);
        return mav;
    }
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView user(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.addObject("content", "user/content/userPage");
        return mav;
    }

    @RequestMapping(value = "userPage/editPage/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.addObject("validationRules", validationContext.get(User.class));
        mav.addObject("content", "user/content/editPage");
        User us=userService.findById(id);
        mav.addObject("user", us);
        return mav;
    }
    @RequestMapping(value = "/userPage/{id}", method = RequestMethod.GET)
    public ModelAndView userPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.addObject("user",userService.findById(id));
        mav.addObject("content", "user/content/userPage");
        return mav;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return  "redirect:/"+ROOT+"/user/viewAll";
    }

    
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public ModelAndView createUser() {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        validationContext.add(User.class);
        mav.addObject("validationRules", validationContext.get(User.class));
        mav.addObject("content", "user/content/createContent");
        return mav;
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute(ROOT_MODEL) @Valid User user,
                             BindingResult bindingResult,
                             SessionStatus session) {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
         mav.addObject("content", "user/content/viewAll");
        
         Collection <Role> rol = new ArrayList<Role>();
         rol.add(new Role());
         user.setAuthorities(rol);
         user=this.userService.create(user);
         session.setComplete();
 Collection<User> listUser=userService.findAll();
        mav.addObject("users", listUser);
    
        return mav;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable Integer id, @ModelAttribute(ROOT_MODEL) @Valid User user,
                             BindingResult bindingResult,
                             SessionStatus session) {
        ModelAndView mav = new ModelAndView(ROOT + "/layout");
        mav.addObject("content", "user/content/viewAll");

        Collection <Role> rol = new ArrayList<Role>();
        rol.add(new Role());
        user.setAuthorities(rol);
        user.setId(id);

        this.userService.update(user);
        session.setComplete();
        Collection<User> listUser=userService.findAll();
        mav.addObject("users", listUser);

        return mav;
    }
      
    @ModelAttribute(value = ROOT_MODEL)
    public User getCommandObject() {
        return new User();
    }
     @InitBinder(ROOT_MODEL)
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Collection.class, new CustomTagsEditor());
    }
    
//     @ModelAttribute("searchFilter")
//    public SearchFilter getFilter() {
//        return new SearchFilter();
//    }
//    
     @ModelAttribute("roles")
    public Collection<String> getCategories() {

        Collection<String> s = new ArrayList<String>();
        s.add(Roles.ROLE_ADMIN.name());
        s.add(Roles.ROLE_USER.name());
        s.add(Roles.ROLE_EXPERT.name());
        return s;
    }

      @ModelAttribute("english")
    public Collection<String> getEnglish() {
        
        Collection<String> s = new ArrayList<String>();
        s.add("Basic");
        s.add("Intermidiate");
        s.add("Advanced");
        return s;
    }
    

}

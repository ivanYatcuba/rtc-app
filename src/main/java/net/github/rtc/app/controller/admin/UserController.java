package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.RoleService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.propertyeditors.CustomStringEditor;
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
import java.util.List;

/**
 * @author  Lapshin Ugene
 */
@Controller ("adminNavigationController")
@RequestMapping ("admin/user")
public class UserController {

    @Autowired
    private ValidationContext validationContext;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private static final String ROOT = "portal/admin";
    private static final String ROOT_MODEL = "user";


    @RequestMapping(value = "/viewAll", method = RequestMethod.GET)
    public ModelAndView viewAll() {
        ModelAndView mav = new ModelAndView(ROOT + "/page/viewAllusers");
        Collection<User> listUser=userService.findAll();
        mav.addObject("users", listUser);
        return mav;
    }
    @RequestMapping(value = "/view/{code}", method = RequestMethod.GET)
    public ModelAndView user(@PathVariable Integer code) {
        ModelAndView mav = new ModelAndView(ROOT + "/page/userPagea");
        return mav;
    }

    @RequestMapping(value = "userPage/editPage/{code}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable String code) {
        ModelAndView mav = new ModelAndView(ROOT + "/page/editPages");
        mav.addObject("validationRules", validationContext.get(User.class));
        User us=userService.findByCode(code);
        mav.addObject("user", us);
        return mav;
    }
    @RequestMapping(value = "/userPage/{code}", method = RequestMethod.GET)
    public ModelAndView userPage(@PathVariable String code) {
        ModelAndView mav = new ModelAndView(ROOT + "/page/userPagea");
        mav.addObject("user",userService.findByCode(code));
        return mav;
    }

    @RequestMapping(value = "/delete/{code}", method = RequestMethod.GET)
    public String delete(@PathVariable String code) {
        userService.deleteByCode(code);
        return  "redirect:/"+ROOT+"/user/viewAll";
    }

    
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public ModelAndView createUser() {
        ModelAndView mav = new ModelAndView(ROOT + "/page/createuser");
        mav.addObject("validationRules", validationContext.get(User.class));
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute(ROOT_MODEL) @Valid User user,
                             SessionStatus session, @RequestParam RoleType selectedRole) {
        List<Role> useRoles = new ArrayList<>();
        useRoles.add(roleService.getRoleByType(selectedRole));
        user.setAuthorities(useRoles);
        userService.create(user);
        session.setComplete();
        return "redirect:viewAll";
    }

    @RequestMapping(value = "/update/{code}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable String code, @ModelAttribute(ROOT_MODEL) @Valid User user,
                             BindingResult bindingResult,
                             SessionStatus session) {
        ModelAndView mav = new ModelAndView(ROOT + "/page/viewAllusers");
        List<Role> rol = new ArrayList<Role>();
        rol.add(new Role());
        user.setAuthorities(rol);
        user.setCode(code);

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
        binder.registerCustomEditor(Collection.class, new CustomStringEditor());
    }


    @ModelAttribute("roles")
    public List<RoleType> getCategories() {

        List<RoleType> roles = new ArrayList<>();
        roles.add(RoleType.ROLE_USER);
        roles.add(RoleType.ROLE_ADMIN);
        roles.add(RoleType.ROLE_EXPERT);

        return roles;
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

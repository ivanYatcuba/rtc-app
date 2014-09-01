package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.propertyeditors.CustomStringEditor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private static final String ROOT = "portal/admin";
    private static final String ROOT_MODEL = "user";
    private static final int USERS_PER_PAGE = 10;


    @RequestMapping(value = "/viewAll", method = RequestMethod.GET)
    public ModelAndView viewAll() {
        ModelAndView mav = new ModelAndView(ROOT + "/page/viewAllusers");
        Collection<User> listUser= userService.findAll();
        ArrayList<User> NewListUser = new ArrayList<User>();
        for(User user:listUser)NewListUser.add(user);
        int numberOfPage = 1;
        int lastUser = USERS_PER_PAGE*numberOfPage;
        int firstUser = lastUser - USERS_PER_PAGE;
        if (lastUser>NewListUser.size())lastUser = NewListUser.size();
        int numbOfPages = (int)Math.ceil(listUser.size()/(double)USERS_PER_PAGE);
        mav.addObject("users", NewListUser.subList(firstUser,lastUser));
        mav.addObject("pages", numbOfPages);
        mav.addObject("numberOfPage", numberOfPage);
        return mav;
    }

    @RequestMapping(value = "/viewAll/{numberOfPage}", method = RequestMethod.GET)
    public ModelAndView viewAll(@PathVariable int numberOfPage) {
        ModelAndView mav = new ModelAndView(ROOT + "/page/viewAllusers");
        Collection<User> listUser= userService.findAll();
        ArrayList<User> NewListUser = new ArrayList<>();
        for(User user:listUser)NewListUser.add(user);
        int lastUser = USERS_PER_PAGE*numberOfPage;
        int firstUser = lastUser - USERS_PER_PAGE;
        if (lastUser>NewListUser.size())lastUser = NewListUser.size();
        int numbOfPages = (int)Math.ceil(listUser.size()/(double)USERS_PER_PAGE);
        mav.addObject("users", NewListUser.subList(firstUser,lastUser));
        mav.addObject("pages", numbOfPages);
        mav.addObject("numberOfPage", numberOfPage);
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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam String userCode) {
        //1. Do not forget to remove it
        //2. Use logs
        //System.out.print("1111111111111");
        userService.deleteByCode(userCode);
        return  "redirect:/admin/user/viewAll";
    }

    @RequestMapping(value = "/expertUsers", method = RequestMethod.POST)
    public @ResponseBody List<String> getExpertUsers() {
        List<String> results = new ArrayList<>();
        List<User> users = userService.getUserByRole(RoleType.ROLE_EXPERT);
        for(User user : users){
            results.add(user.shortString());
        }
        return  results;
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
        user.setAuthorities(Arrays.asList(userService.getRoleByType(selectedRole)));
        user.setRegisterDate(new Date());
        userService.create(user);
        session.setComplete();
        return "redirect:/admin/user/viewAll";
    }

    @RequestMapping(value = "/update/{code}", method = RequestMethod.POST)
    public String update(@PathVariable String code, @ModelAttribute(ROOT_MODEL) @Valid User user,
                             BindingResult bindingResult,
                             SessionStatus session, @RequestParam RoleType selectedRole) {
        user.setAuthorities(Arrays.asList(userService.getRoleByType(selectedRole)));
        user.setCode(code);
        user.setId(userService.findByCode(user.getCode()).getId());
        userService.update(user);
        session.setComplete();
        return "redirect:/admin/user/userPage/" + user.getCode();
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

    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    

}

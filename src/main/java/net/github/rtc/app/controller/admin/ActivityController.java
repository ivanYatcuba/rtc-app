package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.activity.Activity;
import net.github.rtc.app.model.activity.ActivityAction.ActivityAction;
import net.github.rtc.app.model.activity.ActivityAction.ActivityActionType;
import net.github.rtc.app.model.activity.entityList.EntityList;
import net.github.rtc.app.model.activity.entityList.EntityType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.activity.ActivityService;
import net.github.rtc.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by Alexey Samoylov on 23.01.2015.
 */
@Controller("activityController")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public
    @ResponseBody
    Activity getActivityTable() {
        EntityList e = new EntityList(EntityType.COURSE);
        ActivityAction aa = new ActivityAction(ActivityActionType.SAVED);

        Activity a = new Activity(e,"details", getCurrentUser(), aa, new Date());
        System.out.print("-------a"+a);
        try {
            activityService.create(a);
        } catch(Exception ex) {System.out.print("*********create"+ex);}

        System.out.print("*********create");
        return a;
    }

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        return userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}

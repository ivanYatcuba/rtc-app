package net.github.rtc.app.export;

import net.github.rtc.app.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import net.github.rtc.app.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class JobRemovingUser {

    @Autowired
    private UserService userService;

    @Scheduled(fixedDelay =300000)
    public void deletingUser(){
        Collection<User> listUsers = userService.findAll();
        for(User user: listUsers){
            if(user.getRemovalDate() != null){
               String userCode = user.getCode();
                userService.deleteByCode(userCode);
            }
        }
    }
}

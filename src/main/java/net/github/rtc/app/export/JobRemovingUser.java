package net.github.rtc.app.export;

import net.github.rtc.app.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import net.github.rtc.app.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class JobRemovingUser {
    private static Logger LOG = LoggerFactory.getLogger(JobRemovingUser.class.getName());

    @Autowired
    private UserService userService;

    @Scheduled(fixedDelay =300000)
    public void deletingUser(){
        LOG.info("JobRemovingUser started");
        Collection<User> listUsers = userService.findAll();
        LOG.info("Size of downloaded collection: {}", listUsers.size());
        for(User user: listUsers){
            if(user.getRemovalDate() != null){
               String userCode = user.getCode();
                userService.deleteByCode(userCode);
            }
        }
    }
}

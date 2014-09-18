package net.github.rtc.app.export;

import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class JobRemovingUser {

    public static final int JOB_DELAY = 300000;

    private static Logger log = LoggerFactory.getLogger(
      JobRemovingUser.class.getName());

    @Autowired
    private UserService userService;

    @Scheduled(fixedDelay = JOB_DELAY)
    public void deletingUser() {
        log.debug("JobRemovingUser started");
        final Collection<User> listUsers = userService.findAll();
        log.debug("Size of downloaded collection: {}", listUsers.size());
        for (final User user : listUsers) {
            if (user.getRemovalDate()
              != null) {
                final String userCode = user.getCode();
                userService.deleteByCode(userCode);
            }
        }
    }
}

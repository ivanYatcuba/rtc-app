package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.UserDao;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserServiceLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vladislav Pikus
 */
@Service("userDetailsService")
public class UserServiceLoginImpl implements UserDetailsService,
  UserServiceLogin {

    private static final Logger LOG = LoggerFactory.getLogger(
            UserServiceLoginImpl.class.getName());

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User loadUserByUsername(final String email) {
        LOG.info("Loading user with email: "
                + email);
        return userDao.findByEmail(email);
    }
}

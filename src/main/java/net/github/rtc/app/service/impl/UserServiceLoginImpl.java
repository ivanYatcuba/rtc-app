package net.github.rtc.app.service.impl;

import net.github.rtc.app.model.User;
import net.github.rtc.app.resource.UserResource;
import net.github.rtc.app.service.UserServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vladislav Pikus
 */
@Service("userDetailsService")
public class UserServiceLoginImpl implements UserDetailsService, UserServiceLogin {

    @Autowired
    private UserResource userResource;

    @Override
    @Transactional
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userResource.findByEmail(email);
    }
}

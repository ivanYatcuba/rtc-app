package rtc.app.service;

import net.github.rtc.web.user.model.User;
import net.github.rtc.web.user.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Vladislav Pikus
 */
@Service("userDetailsService")
public class UserService implements UserDetailsService {

    private UserResource userResource;

    @Autowired
    public void setUserResource(UserResource userResource) {
        this.userResource = userResource;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userResource.findByEmail(email);
    }
}

package net.github.rtc.web.user.service.impl;

import net.github.rtc.web.user.model.User;
import net.github.rtc.web.user.resource.UserResource;
import net.github.rtc.web.user.service.UserService;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Service Implementation
 * This's a wrapper for {@link net.github.rtc.web.courses.service.CoursesService}
 *
 * @author Vladislav Pikus
 * @author Dmitry Pritula
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    //private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    @Autowired
    public void setResource(UserResource resource) {
        this.resource = resource;
    }

    private UserResource resource;



    @Override
    public User findById(Integer id) {

        return resource.findById(id);

    }

    @Override
    public Collection<User> findAll() {
        return resource.findAll();
    }

    @Override
    public User create(User user) {
        return resource.create(user);
    }

}

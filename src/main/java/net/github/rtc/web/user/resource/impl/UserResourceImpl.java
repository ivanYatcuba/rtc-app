package net.github.rtc.web.user.resource.impl;

import net.github.rtc.web.user.model.User;
import net.github.rtc.web.user.resource.UserResource;
import org.springframework.stereotype.Component;


@Component("UserDao")
public class UserResourceImpl extends AbstractResource implements UserResource {


    @Override
    public User findById(Integer id) {
        return restTemplate.getForObject(hostUrl + "user/{id}", User.class, id);
    }

}

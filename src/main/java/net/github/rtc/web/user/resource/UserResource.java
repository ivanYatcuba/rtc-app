package net.github.rtc.web.user.resource;

import net.github.rtc.web.user.model.User;

import java.util.Collection;


public interface UserResource {

    User findById (Integer id);

    Collection<User> findAll();

}

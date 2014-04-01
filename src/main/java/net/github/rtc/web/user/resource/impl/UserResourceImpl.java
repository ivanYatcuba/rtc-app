package net.github.rtc.web.user.resource.impl;

import net.github.rtc.web.user.model.User;
import net.github.rtc.web.user.resource.UserResource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;


@Component("UserDao")
public class UserResourceImpl extends AbstractResource implements UserResource {
 protected String userUrl;
    public String getHostUrl() {
        return hostUrl;
    }
    public String getUserUrl()
    {
        return this.userUrl;
    }
    public void setUserUrl(String userUrl)
    {
        this.userUrl=userUrl;
    }

    @Override
    public User findById(Integer id) {
        return restTemplate.getForObject(userUrl + "user/{id}", User.class, id);
    }

    @Override
    public Collection<User> findAll() {
       // return Arrays.asList(restTemplate.getForObject(userUrl + "users", User[].class));
        String request = userUrl+"viewAll";
        return Arrays.asList(restTemplate.getForObject(request, User[].class));
    }

}

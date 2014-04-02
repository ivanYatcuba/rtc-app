package net.github.rtc.web.user.resource.impl;

import net.github.rtc.web.user.model.User;
import net.github.rtc.web.user.resource.UserResource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


@Component("UserDao")
public class UserResourceImpl  implements UserResource {
 protected String userUrl;
 protected RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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

 @Override
     public User create(User user) {
        return restTemplate.postForObject(userUrl, user, User.class);
    }

    @Override
    public void delete(Integer id) {
         restTemplate.delete(userUrl + "user/{id}", id);
    }
}

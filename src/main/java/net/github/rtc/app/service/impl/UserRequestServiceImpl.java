package net.github.rtc.app.service.impl;

import net.github.rtc.app.model.UserRequest;
import net.github.rtc.app.resource.UserRequestResource;
import net.github.rtc.app.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
@Service
public class UserRequestServiceImpl implements UserRequestService {
    @Autowired
    UserRequestResource resource;

    @Override
    public UserRequest getUserRequest(long id) {
        return null;
    }

    @Override
    public List<UserRequest> getAll() {
        return null;
    }

    @Override
    public void update(UserRequest request) {

    }

    @Override
    public void delete(UserRequest request) {

    }
}

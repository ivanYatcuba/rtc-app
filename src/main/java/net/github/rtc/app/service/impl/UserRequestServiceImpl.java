package net.github.rtc.app.service.impl;

import net.github.rtc.app.model.UserRequest;
import net.github.rtc.app.resource.UserRequestResource;
import net.github.rtc.app.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
@Service
public class UserRequestServiceImpl implements UserRequestService {
    @Autowired
    UserRequestResource resource;


    @Override
    @Transactional
    public void insert(UserRequest request) {
        resource.insert(request);
    }

    @Override
    @Transactional
    public UserRequest getUserRequest(long id) {
        return resource.findByID(id);
    }

    @Override
    @Transactional
    public List<UserRequest> getAll() {
        return resource.getAll();
    }

    @Override
    @Transactional
    public void update(UserRequest request) {
        resource.update(request);
    }

    @Override
    @Transactional
    public void delete(UserRequest request) {
        resource.delete(request);
    }
}

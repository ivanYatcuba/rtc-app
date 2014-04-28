package net.github.rtc.app.service.impl;

import net.github.rtc.app.model.UserCourseOrder;
import net.github.rtc.app.model.UserRequestStatus;
import net.github.rtc.app.resource.UserCourseOrderResource;
import net.github.rtc.app.service.UserCourseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
@Service
public class UserCourseOrderServiceImpl implements UserCourseOrderService {
    @Autowired
    UserCourseOrderResource resource;


    @Override
    @Transactional
    public void insert(UserCourseOrder order) {
        resource.insert(order);
    }

    @Override
    @Transactional
    public UserCourseOrder getUserOrder(long id) {
        return resource.findByID(id);
    }

    @Override
    @Transactional
    public List<UserCourseOrder> getAll() {
        return resource.getAll();
    }

    @Override
    @Transactional
    public void update(UserCourseOrder order) {
        resource.update(order);
    }

    @Override
    @Transactional
    public void delete(UserCourseOrder request) {
        resource.delete(request);
    }

    @Override
    @Transactional
    public UserCourseOrder getUserOrderByUserId(long userId) {
        return resource.getUserOrder(userId);
    }

    @Override
    @Transactional
    public List<UserCourseOrder> getOrderByStatus(UserRequestStatus status) {
        return resource.getOrderByStatus(status);
    }

}

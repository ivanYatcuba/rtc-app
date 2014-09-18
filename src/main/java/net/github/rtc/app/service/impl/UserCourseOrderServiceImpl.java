package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.UserCourseOrderDao;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.model.user.UserRequestStatus;
import net.github.rtc.app.service.UserCourseOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
@Service
public class UserCourseOrderServiceImpl implements UserCourseOrderService {

    private static Logger log = LoggerFactory.getLogger(
            UserCourseOrderServiceImpl.class.getName());

    @Autowired
    private UserCourseOrderDao resource;

    @Override
    @Transactional
    public void insert(final UserCourseOrder order) {
        log.info("Creating order: "
                + order);
        resource.create(order);
    }

    @Override
    @Transactional
    public UserCourseOrder getUserOrder(final long id) {
        log.info("Getting user course order with id: "
                + id);
        return resource.find(id);
    }

    @Override
    @Transactional
    public List<UserCourseOrder> getAll() {
        log.info("Getting all orders from database..");
        return (List) resource.findAll();
    }

    @Override
    @Transactional
    public void update(final UserCourseOrder order) {
        log.info("Updating user course order: "
                + order);
        resource.update(order);
    }

    @Override
    @Transactional
    public void delete(final UserCourseOrder order) {
        log.info("Removing user order: "
                + order);
        resource.delete(order.getId());
    }

    @Override
    @Transactional
    public UserCourseOrder getUserOrderByUserCode(final String userCode) {
        log.info("Get user order by user code: "
                + userCode);
        return resource.getUserOrder(userCode);
    }

    @Override
    @Transactional
    public List<UserCourseOrder> getOrderByStatus(
      final UserRequestStatus status) {
        log.info("Get user orders with status: "
                + status);
        return resource.getOrderByStatus(status);
    }

}

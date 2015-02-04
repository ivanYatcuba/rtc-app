package net.github.rtc.app.service.impl.user;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dao.UserCourseOrderDao;
import net.github.rtc.app.model.user.UserCourseOrder;
import net.github.rtc.app.model.user.UserRequestStatus;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.impl.genericservise.AbstractGenericServiceImpl;
import net.github.rtc.app.service.user.UserCourseOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserCourseOrderServiceImpl extends AbstractGenericServiceImpl<UserCourseOrder> implements
  UserCourseOrderService {

    private static Logger log = LoggerFactory.getLogger(UserCourseOrderServiceImpl.class.getName());
    @Autowired
    private UserCourseOrderDao userCourseOrderDao;
    @Autowired
    private DateService dateService;

    @Override
    @Transactional
    public UserCourseOrder getUserOrderByUserCode(final String userCode) {
        log.info("Get user order by user code: " + userCode);
        return userCourseOrderDao.getUserOrder(userCode);
    }

    @Override
    @Transactional
    public List<UserCourseOrder> getOrderByStatus(
      final UserRequestStatus status) {
        log.info("Get user orders with status: " + status);
        return userCourseOrderDao.getOrderByStatus(status);
    }

  @Override
  public List<UserCourseOrder> getOrderByExpertCode(String expertCode) {
    return userCourseOrderDao.getOrderByExpert(expertCode);
  }

  @Override
  public int getAcceptedOrdersForCourse(String courseCode) {
    return userCourseOrderDao.getAcceptedOrdersForCourse(courseCode);
  }

  @Override
  public boolean changeOrderStatus(UserRequestStatus userRequestStatus, String orderCode) {
    final UserCourseOrder order = findByCode(orderCode);
    order.setResponseDate(dateService.getCurrentDate());
    order.setStatus(userRequestStatus);
    try {
      update(order);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @Override
    protected GenericDao<UserCourseOrder> getDao() {
        return userCourseOrderDao;
    }
}

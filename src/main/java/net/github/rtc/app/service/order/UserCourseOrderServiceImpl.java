package net.github.rtc.app.service.order;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.dao.order.UserCourseOrderDao;
import net.github.rtc.app.model.dto.user.ExpertOrderDTO;
import net.github.rtc.app.model.dto.builder.ExpertOrderDtoBuilder;
import net.github.rtc.app.model.entity.course.CourseType;
import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.model.entity.order.UserRequestStatus;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.generic.AbstractGenericServiceImpl;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.AuthorizedUserProvider;
import net.github.rtc.app.utils.datatable.search.SearchResultsBuilder;
import net.github.rtc.app.utils.datatable.search.SearchResultsMapper;
import net.github.rtc.app.utils.datatable.search.filter.OrderSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserCourseOrderServiceImpl extends AbstractGenericServiceImpl<UserCourseOrder> implements
  UserCourseOrderService {

    private static Logger log = LoggerFactory.getLogger(UserCourseOrderServiceImpl.class.getName());
    @Autowired
    private UserCourseOrderDao userCourseOrderDao;
    @Autowired
    private DateService dateService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @Override
    @Transactional
    public UserCourseOrder getUserOrderByUserCode(final String userCode) {
        log.info("Get user order by user code: " + userCode);
        return userCourseOrderDao.getUserOrder(userCode);
    }

    @Override
    @Transactional
    public List<UserCourseOrder> getUserOrdersByCode(String userCode) {
        log.info("Get user orders by user code: " + userCode);
        return userCourseOrderDao.getUserOrdersByCode(userCode);
    }

    @Override
    @Transactional
    public List<UserCourseOrder> getOrderByStatus(
      final UserRequestStatus status) {
        log.info("Get user orders with status: " + status);
        return userCourseOrderDao.getOrderByStatus(status);
    }

    @Override
    public SearchResults<ExpertOrderDTO> searchOrderForExpert(OrderSearchFilter searchFilter) {
        final SearchResultsBuilder<UserCourseOrder, ExpertOrderDTO> resultsBuilder = new SearchResultsBuilder<>();
        return resultsBuilder.setSearchResultsToTransform(search(searchFilter)).
            setSearchResultsMapper(getOrderToExpertOrderMapper()).build();
    }

  @Override
  public int getAcceptedOrdersCount(String courseCode) {
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
    @Transactional
    public UserCourseOrder create(String courseCode, CourseType position) {
        final Date now = LocalDate.now().toDate();
        final String userCode = AuthorizedUserProvider.getAuthorizedUser().getCode();

        final UserCourseOrder userCourseOrder = new UserCourseOrder();
        userCourseOrder.setCourseCode(courseCode);
        userCourseOrder.setPosition(position);
        userCourseOrder.setUserCode(userCode);
        userCourseOrder.setRequestDate(now);
        userCourseOrder.setReason("prosto tak");
        return super.create(userCourseOrder);
    }

    @Override
    public void acceptOrder(String orderCode) {
        final UserCourseOrder order = findByCode(orderCode);
        order.setStatus(UserRequestStatus.ACCEPTED);
        order.setResponseDate(LocalDate.now().toDate());
        super.update(order);
        courseService.addParticipant(order.getCourseCode(), order.getUserCode());
    }

    @Override
    protected GenericDao<UserCourseOrder> getDao() {
        return userCourseOrderDao;
    }

    private SearchResultsMapper<UserCourseOrder, ExpertOrderDTO> getOrderToExpertOrderMapper() {
        return new SearchResultsMapper<UserCourseOrder, ExpertOrderDTO>() {
            @Override
            public List<ExpertOrderDTO> map(List<UserCourseOrder> searchResults) {
                final List<ExpertOrderDTO> outputResults = new ArrayList<>();
                for (UserCourseOrder order: searchResults) {
                    final ExpertOrderDtoBuilder dtoBuilder = new ExpertOrderDtoBuilder();
                    outputResults.add(dtoBuilder.setOrder(order).
                            setCourse(courseService.findByCode(order.getCourseCode())).
                            setAcceptedOrders(getAcceptedOrdersCount(order.getCourseCode())).
                            setUser(userService.findByCode(order.getUserCode())).build());
                }
                return outputResults;
            }
        };
    }
}

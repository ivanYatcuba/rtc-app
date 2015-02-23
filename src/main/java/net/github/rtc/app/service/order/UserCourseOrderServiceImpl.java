package net.github.rtc.app.service.order;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.dao.order.UserCourseOrderDao;
import net.github.rtc.app.model.dto.user.ExpertOrderDTO;
import net.github.rtc.app.service.builder.ExpertOrderDtoBuilder;
import net.github.rtc.app.model.entity.course.CourseType;
import net.github.rtc.app.model.entity.order.UserCourseOrder;
import net.github.rtc.app.model.entity.order.UserRequestStatus;
import net.github.rtc.app.service.course.CourseService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.generic.AbstractGenericServiceImpl;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.service.security.AuthorizedUserProvider;
import net.github.rtc.app.service.builder.SearchResultsBuilder;
import net.github.rtc.app.service.builder.SearchResultsMapper;
import net.github.rtc.app.model.dto.filter.OrderSearchFilter;
import net.github.rtc.app.model.dto.SearchResults;
import net.github.rtc.app.utils.message.event.MessageEventCreator;
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

    private static final Logger LOG = LoggerFactory.getLogger(UserCourseOrderServiceImpl.class.getName());
    @Autowired
    private UserCourseOrderDao userCourseOrderDao;
    @Autowired
    private DateService dateService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private MessageEventCreator eventCreator;

    @Override
    @Transactional
    public UserCourseOrder getUserOrderByUserCode(final String userCode) {
        LOG.info("Get user order by user code: " + userCode);
        return userCourseOrderDao.getUserOrder(userCode);
    }

    @Override
    @Transactional
    public List<UserCourseOrder> getUserOrdersByCode(String userCode) {
        LOG.info("Get user orders by user code: " + userCode);
        return userCourseOrderDao.getUserOrdersByCode(userCode);
    }

    @Override
    public SearchResults<ExpertOrderDTO> searchOrderForExpert(OrderSearchFilter searchFilter) {
        final SearchResultsBuilder<UserCourseOrder, ExpertOrderDTO> resultsBuilder = new SearchResultsBuilder<>();
        return resultsBuilder.setSearchResultsToTransform(search(searchFilter)).
            setSearchResultsMapper(getOrderToExpertOrderMapper()).build();
    }

  @Override
  public int getAcceptedOrdersCount(String courseCode) {
    return userCourseOrderDao.getAcceptedOrdersCourseCount(courseCode);
  }

  @Override
  public boolean changeOrderStatus(String orderCode, UserRequestStatus userRequestStatus) {
    final UserCourseOrder order = findByCode(orderCode);
    order.setResponseDate(dateService.getCurrentDate());
    order.setStatus(userRequestStatus);
    try {
      update(order);
    } catch (Exception e) {
      return false;
    }
    eventCreator.createOrderResponseMessageEvent(order);
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

        eventCreator.createOrderSendMessageEvent(userCourseOrder);

        return super.create(userCourseOrder);
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
                    outputResults.add(dtoBuilder.buildOrderFields(order).
                            buildCourseFields(courseService.findByCode(order.getCourseCode())).
                            buildAcceptedOrders(getAcceptedOrdersCount(order.getCourseCode())).
                            buildUserFields(userService.findByCode(order.getUserCode())).get());
                }
                return outputResults;
            }
        };
    }
}

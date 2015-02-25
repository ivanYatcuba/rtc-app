package net.github.rtc.app.service.order;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.dao.order.UserCourseOrderDao;
import net.github.rtc.app.model.dto.user.ExpertOrderDto;
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
    public SearchResults<ExpertOrderDto> searchOrderForExpert(OrderSearchFilter searchFilter) {
        final SearchResultsBuilder<UserCourseOrder, ExpertOrderDto> resultsBuilder = new SearchResultsBuilder<>();
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
        userCourseOrder.setReason("");

        eventCreator.createOrderSendMessageEvent(userCourseOrder);

        return super.create(userCourseOrder);
    }

    @Override
    @Transactional
    public UserCourseOrder getUserCourseOrder(String userCode, String courseCode) {
        return userCourseOrderDao.getUserCourseOrder(userCode, courseCode);
    }

    @Override
    protected GenericDao<UserCourseOrder> getDao() {
        return userCourseOrderDao;
    }

    /**
     * Returns an object that map list of orders to orderDTOs list
     * @return anonymous class mapper
     */
    private SearchResultsMapper<UserCourseOrder, ExpertOrderDto> getOrderToExpertOrderMapper() {
        return new SearchResultsMapper<UserCourseOrder, ExpertOrderDto>() {
            @Override
            public List<ExpertOrderDto> map(List<UserCourseOrder> searchResults) {
                final List<ExpertOrderDto> outputResults = new ArrayList<>();
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

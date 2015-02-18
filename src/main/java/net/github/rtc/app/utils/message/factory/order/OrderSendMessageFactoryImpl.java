package net.github.rtc.app.utils.message.factory.order;

        import net.github.rtc.app.model.entity.course.Course;
        import net.github.rtc.app.model.entity.message.Message;
        import net.github.rtc.app.model.entity.message.MessageType;
        import net.github.rtc.app.model.entity.order.UserCourseOrder;
        import net.github.rtc.app.model.entity.user.User;
        import net.github.rtc.app.service.course.CourseService;
        import net.github.rtc.app.service.date.DateService;
        import net.github.rtc.app.utils.StringFromTemplateBuilder;
        import net.github.rtc.app.utils.message.MessageBuilder;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.config.BeanDefinition;
        import org.springframework.context.annotation.Scope;
        import org.springframework.stereotype.Component;

        import java.util.ArrayList;
        import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderSendMessageFactoryImpl implements OrderMessageFactory {

    private static final String NEW_ORDER_TEMPLATE_PATH = "/templates/messages/newOrderMessage.ftl";


    private UserCourseOrder order;

    @Autowired
    private StringFromTemplateBuilder stringFromTemplateBuilder;
    @Autowired
    private DateService dateService;
    @Autowired
    private CourseService courseService;

    @Override
    public void setOrder(UserCourseOrder order) {
        this.order = order;
    }

    @Override
    public List<Message> getMessages() {
        final List<Message> msgList = new ArrayList<>();

        final MessageBuilder messageBuilder = new MessageBuilder().setSenderCode(order.getUserCode()).
                setSendingDate(dateService.getCurrentDate()).
                setType(MessageType.SYSTEM).setText(getMessageText());

        final Course course = courseService.findByCode(order.getCourseCode());

        for (User expert : course.getExperts()) {
            final Message msg = messageBuilder.setReceiverCode(expert.getCode()).build();
            msgList.add(msg);
        }
        return msgList;
    }

    private String getMessageText() {
            return stringFromTemplateBuilder.setTemplate(NEW_ORDER_TEMPLATE_PATH).build();
        }

}

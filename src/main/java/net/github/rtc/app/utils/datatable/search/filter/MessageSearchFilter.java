package net.github.rtc.app.utils.datatable.search.filter;

import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.model.entity.message.MessageStatus;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

public class MessageSearchFilter extends AbstractSearchCommand {

    private static final String SENDING_DATE = "sendingDate";
    private static final String IS_READ = "isRead";
    private static final String RECEIVER_USER_CODE = "receiverUserCode";


    private MessageStatus status;
    private Date sendingDate;
    private char dateMoreLessEq;
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }


    public Date getSendingDate() {
        return sendingDate == null ? null : new Date(sendingDate.getTime());
    }

    public void setSendingDate(Date sendingDate) {
        if (sendingDate != null) {
            this.sendingDate = new Date(sendingDate.getTime());
        }
    }

    public char getDateMoreLessEq() {
        return dateMoreLessEq;
    }

    public void setDateMoreLessEq(char dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }

    @Override
    public Order order() {
        return Order.asc(SENDING_DATE);
    }

    @Override
    public DetachedCriteria getCriteria() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(Message.class);

        if (userCode != null && !("").equals(userCode)) {
            criteria.add(Restrictions.eq(RECEIVER_USER_CODE, userCode));
        }

        if (sendingDate != null) {
            final DateCriteriaCreator dateCriteriaCreator = new DateCriteriaCreator(SENDING_DATE, sendingDate);
            criteria.add(dateCriteriaCreator.getDateCriteria(dateMoreLessEq));
        }
        if (status != null) {
            switch (status) {
                case READ:  criteria.add(Restrictions.eq(IS_READ, true));
                    break;
                case UNREAD: criteria.add(Restrictions.eq(IS_READ, false));
                    break;
                default: break;
            }

        }

        return criteria;
    }
}

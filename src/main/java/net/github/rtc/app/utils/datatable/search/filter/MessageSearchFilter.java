package net.github.rtc.app.utils.datatable.search.filter;

import net.github.rtc.app.model.entity.message.Message;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

public class MessageSearchFilter extends AbstractSearchCommand {

    private static final String SENDING_DATE = "sendingDate";
    private static final String IS_READ = "isRead";

    private Boolean isRead;
    private Date sendingDate;
    private char dateMoreLessEq;

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
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
        if (sendingDate != null) {
            final DateCriteriaCreator dateCriteriaCreator = new DateCriteriaCreator(SENDING_DATE, sendingDate);
            criteria.add(dateCriteriaCreator.getDateCriteria(dateMoreLessEq));
        }
        if (isRead != null) {
            criteria.add(Restrictions.eq(IS_READ, isRead));
        }
        return criteria;
    }
}

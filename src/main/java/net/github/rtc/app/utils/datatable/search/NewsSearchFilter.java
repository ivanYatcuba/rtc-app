package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.model.entity.news.NewsStatus;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Calendar;
import java.util.Date;

public class NewsSearchFilter extends AbstractSearchCommand {

    private static final String PERCENT = "%";
    private static final String CREATE_DATE = "createDate";
    private static final String AUTHOR = "author";

    private String title;

    private String authorCode;

    private NewsStatus status;

    private Date createDate;

    private char dateMoreLessEq;

    @Override
    public Order order() {
        return Order.asc(CREATE_DATE);
    }

    @Override
    public DetachedCriteria getCriteria() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(News.class);
        if (title != null && !("").equals(title)) {
            criteria.add(Restrictions.like("title", PERCENT + title + PERCENT));
        }
        if (createDate != null) {
            switch (dateMoreLessEq) {
                case '>':
                    final Date fromDate = setNightTime(createDate);
                    criteria.add(Restrictions.gt(CREATE_DATE, fromDate));
                    break;
                case '=':
                    final Date toDate = setNightTime(createDate);
                    criteria.add(Restrictions.between(CREATE_DATE, createDate, toDate));
                    break;
                case '<':
                    criteria.add(Restrictions.lt(CREATE_DATE, createDate));
                    break;
                default:
                    break;
            }
        }

        if (status != null) {
            criteria.add(Restrictions.eq("status", status));
        }

        if (authorCode != null && !("").equals(authorCode)) {
            criteria.createAlias(AUTHOR, AUTHOR);
            criteria.add(Restrictions.eq("author.code", authorCode));
        }
        return criteria;
    }

    private Date setNightTime(Date currentDate) {
        final int hour = 23;
        final int minuteSecond = 59;
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minuteSecond);
        calendar.set(Calendar.SECOND, minuteSecond);
        return calendar.getTime();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(final String authorCode) {
        this.authorCode = authorCode;
    }

    public NewsStatus getStatus() {
        return status;
    }

    public void setStatus(NewsStatus status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate == null ? null : new Date(createDate.getTime());
    }

    public void setCreateDate(Date createDate) {
        if (createDate != null) {
            this.createDate = new Date(createDate.getTime());
        }
    }

    public char getDateMoreLessEq() {
        return dateMoreLessEq;
    }

    public void setDateMoreLessEq(char dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }
}

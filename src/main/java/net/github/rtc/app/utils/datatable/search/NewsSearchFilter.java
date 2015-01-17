package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Calendar;
import java.util.Date;

public class NewsSearchFilter extends AbstractSearchCommand {

    private static final String STRING_PERCENT = "%";
    private static final String STRING_CREATE_DATE = "createDate";
    private static final String STRING_AUTHOR = "author";

    private String title;

    private Long authorId;

    private NewsStatus status;

    private Date createDate;

    private char dateMoreLessEq;

    @Override
    public DetachedCriteria getCriteria() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(News.class);
        if (title != null && !("").equals(title)) {
            criteria.add(Restrictions.like("title", STRING_PERCENT + title + STRING_PERCENT));
        }
        if (createDate != null) {
            switch (dateMoreLessEq) {
                case '>':
                    final Date fromDate = setNightTime(createDate);
                    criteria.add(Restrictions.gt(STRING_CREATE_DATE, fromDate));
                    break;
                case '=':
                    final Date toDate = setNightTime(createDate);
                    criteria.add(Restrictions.between(STRING_CREATE_DATE, createDate, toDate));
                    break;
                case '<':
                    criteria.add(Restrictions.lt(STRING_CREATE_DATE, createDate));
                    break;
                default:
                    break;
            }
        }

        if (status != null) {
            criteria.add(Restrictions.eq("status", status));
        }

        if (authorId != null) {
            criteria.createAlias(STRING_AUTHOR, STRING_AUTHOR);
            criteria.add(Restrictions.eq("author.id", authorId));
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public NewsStatus getStatus() {
        return status;
    }

    public void setStatus(NewsStatus status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public char getDateMoreLessEq() {
        return dateMoreLessEq;
    }

    public void setDateMoreLessEq(char dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }
}

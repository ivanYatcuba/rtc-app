package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import net.github.rtc.app.model.user.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;


public class NewsSearchFilter extends AbstractSearchCommand {

    private static final String STRING_PERCENT = "%";
    private static final String STRING_CREATE_DATE = "createDate";

    private String title;

    private User author;

    private NewsStatus status = NewsStatus.ALL;

    private Date createDate;

    private char dateMoreLessEq;

    @Override
    public DetachedCriteria getCriteria() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(News.class);
        if (title != null && !("").equals(title)) {
            criteria.add(Restrictions.like("title", STRING_PERCENT + title + STRING_PERCENT));
        }

        if (author != null) {
            criteria.add(Restrictions.eq("author", author));
        }

        if (createDate != null) {
            switch (dateMoreLessEq) {
                case '>':
                    criteria.add(Restrictions.gt(STRING_CREATE_DATE, createDate));
                    break;
                case '=':
                    criteria.add(Restrictions.eq(STRING_CREATE_DATE, createDate));
                    break;
                case '<':
                    criteria.add(Restrictions.lt(STRING_CREATE_DATE, createDate));
                    break;
                default:
                    break;
            }
        }

        /*if (status != null && status != )*/


        return DetachedCriteria.forClass(News.class);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

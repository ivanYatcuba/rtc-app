package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Date;


public class NewsSearchFilter extends AbstractSearchCommand {

    private static final String STRING_PERCENT = "%";
    private static final String STRING_CREATE_DATE = "createDate";

    private String title;

    private String author;                    //todo: use Long authorId

    private NewsStatus status = NewsStatus.ALL;

    private Date createDate;                  //todo: add binder for this

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

        if (status != null && status != NewsStatus.ALL) {
            criteria.add(Restrictions.eq("status", status));
        }

        return criteria;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

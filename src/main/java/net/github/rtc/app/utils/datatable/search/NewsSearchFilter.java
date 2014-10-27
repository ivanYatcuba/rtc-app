package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;

public class NewsSearchFilter extends AbstractSearchCommand {

    private static final String STRING_PERCENT = "%";
    private static final String STRING_CREATE_DATE = "createDate";
    private static final String STRING_AUTHOR = "author";
    private static Logger log = LoggerFactory.getLogger(NewsSearchFilter.class.getName());

    private String title;

    private String authorData;

    private NewsStatus status = NewsStatus.ALL;

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

        if (authorData != null && !("").equals(authorData)) {
            final String[] authorDataList  = authorData.split(" ");
            final String email = authorDataList[2];
            criteria.createAlias(STRING_AUTHOR, STRING_AUTHOR);
            criteria.add(Restrictions.eq("author.email", email));
        }
        return criteria;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorData() {
        return authorData;
    }

    public void setAuthorData(String authorData) {
        this.authorData = authorData;
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

package net.github.rtc.app.model.dto.filter;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Илья on 20.02.2015.
 */

@Component
public class LogsSearchFilter {
    private static final String CREATED_DATE = "createdDate";
    private Date createdDate;
    private char dateMoreLessEq;

    public char getDateMoreLessEq() {
        return dateMoreLessEq;
    }

    public void setDateMoreLessEq(char dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }

    public Date getCreatedDate() {
        return createdDate == null ? null : new Date(createdDate.getTime());
    }

    public void setCreatedDate(final Date createdDate) {
        if (createdDate != null) {
            this.createdDate = new Date(createdDate.getTime());
        }
    }
}

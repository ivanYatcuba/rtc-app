package net.github.rtc.app.model.activity;


import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.util.annotation.ForExport;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Activity extends AbstractPersistenceObject implements java.io.Serializable {

    private String username;
    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 1)
    private Set<ActivityEntity> entity;
    private String detail;
    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 1)
    private Set<ActivityAction> action;
    private Date actionDate;

    public Activity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<ActivityEntity> getEntity() {
        return entity;
    }

    public void setEntity(Set<ActivityEntity> entity) {
        this.entity = entity;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Set<ActivityAction> getAction() {
        return action;
    }

    public void setAction(Set<ActivityAction> action) {
        this.action = action;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
}
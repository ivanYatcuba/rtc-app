package net.github.rtc.app.model.activity;

import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.model.activity.ActivityAction.ActivityAction;
import net.github.rtc.app.model.activity.entityList.EntityList;

import net.github.rtc.app.model.user.User;
import net.github.rtc.util.annotation.validation.Validatable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by Alexey Samoylov on 23.01.2015.
 */

@Entity
@Validatable
public class Activity extends AbstractPersistenceObject implements Serializable {


    /*
    Entity action

    details!  user! time!
    * */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "entity_id")
    private EntityList entity;


    @Column
    private String details;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "action_id")
    private ActivityAction action;

    @NotNull
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;


    public Activity() {
    }

    public Activity(EntityList entity, String details, User user, ActivityAction action,Date createDate) {
        this.entity = entity;this.details = details;this.user = user;this.action = action;this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "entity=" + entity +
                ", details='" + details + '\'' +
                ", user=" + user +
                ", action=" + action +
                ", createDate=" + createDate +
                '}';
    }

    public EntityList getEntity() {
        return entity;
    }

    public void setEntity(EntityList entity) {
        this.entity = entity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActivityAction getAction() {
        return action;
    }

    public void setAction(ActivityAction action) {
        this.action = action;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
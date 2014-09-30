package net.github.rtc.app.model.news;


import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class News extends AbstractPersistenceObject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String title;

    @OneToOne
//    @PrimaryKeyJoinColumn
    @JoinColumn(name = "author_id")
    private User author;

    @Column
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column
    @Enumerated(EnumType.STRING)
    private NewsStatus status = NewsStatus.DRAFT;

    @Column
    private String description;

    public News() {
    }

    public News(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public NewsStatus getStatus() {
        return status;
    }

    public void setStatus(NewsStatus status) {
        this.status = status;
    }
}

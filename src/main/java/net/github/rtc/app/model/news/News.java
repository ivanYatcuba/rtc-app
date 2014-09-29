package net.github.rtc.app.model.news;


import net.github.rtc.app.model.AbstractPersistenceObject;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class News extends AbstractPersistenceObject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String title;

    /*@OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User author;*/

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

    /*public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }*/
}

package net.github.rtc.app.model.news;


import net.github.rtc.app.model.WithCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class News implements Serializable, WithCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String code;

    @Column
    private String title;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}

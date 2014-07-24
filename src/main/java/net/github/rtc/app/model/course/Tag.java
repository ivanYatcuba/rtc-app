package net.github.rtc.app.model.course;


import net.github.rtc.util.annotation.Required;
import net.github.rtc.util.annotation.Validatable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Vladislav Pikus
 */
@Entity
@Validatable
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Required
    @Column
    private String value;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public Tag(String value) {
        this.value = value;
    }

    public Tag() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (value != null ? !value.equals(tag.value) : tag.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tag{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

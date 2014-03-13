package net.github.rtc.web.courses.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Vladislav Pikus
 */
@XmlRootElement
public class Courses implements Serializable {
    private Integer id;

    private String code;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;

    @NotEmpty
    private String type;

    @NotNull
    private Author author;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private List<Tags> tags;

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Courses() {

    }

    public Courses(String code, String name, String type, Author author, Date startDate, Date endDate) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.author = author;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Courses courses = (Courses) o;

        if (author != null ? !author.equals(courses.author) : courses.author != null) return false;
        if (code != null ? !code.equals(courses.code) : courses.code != null) return false;
        if (endDate != null ? !endDate.equals(courses.endDate) : courses.endDate != null) return false;
        if (id != null ? !id.equals(courses.id) : courses.id != null) return false;
        if (name != null ? !name.equals(courses.name) : courses.name != null) return false;
        if (startDate != null ? !startDate.equals(courses.startDate) : courses.startDate != null) return false;
        if (tags != null ? !tags.equals(courses.tags) : courses.tags != null) return false;
        if (type != courses.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Courses{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", author=").append(author);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }
}

package net.github.rtc.app.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
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
public class Course implements Serializable {

    private String code;

    @NotBlank
    @Length(min = 2, max = 30)
    private String name;

    @NotBlank
    private String type;

    @NotNull
    private Author author;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private Date publishDate;

    @NotEmpty
    private List<Tag> tags;

    @NotNull
    private Integer capacity;

    @Size(max = 255)
    private String description;

    @NotNull
    private String status = "DRAFT";

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
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

    public Integer getCapacity() { return capacity;}

    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Date getPublishDate() { return publishDate; }

    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public Course() {

    }

    public Course(String code, String name, String type, Author author, Date startDate, Date endDate,
                  Date publishDate, Integer capacity, String description, String status) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.author = author;
        this.startDate = startDate;
        this.endDate = endDate;
        this.publishDate = publishDate;
        this.capacity = capacity;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Course{");
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", author=").append(author);
        sb.append(", capacity=").append(capacity);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", publishDate=").append(publishDate);
        sb.append(", tags=").append(tags);
        sb.append(", description=").append(description);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (author != null ? !author.equals(course.author) : course.author != null) return false;
        if (code != null ? !code.equals(course.code) : course.code != null) return false;
        if (endDate != null ? !endDate.equals(course.endDate) : course.endDate != null) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        if (startDate != null ? !startDate.equals(course.startDate) : course.startDate != null) return false;
        if (tags != null ? !tags.equals(course.tags) : course.tags != null) return false;
        if (type != null ? !type.equals(course.type) : course.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        return result;
    }
}

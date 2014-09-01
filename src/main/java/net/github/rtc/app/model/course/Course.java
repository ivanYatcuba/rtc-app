package net.github.rtc.app.model.course;

import net.github.rtc.app.annotation.ForExport;
import net.github.rtc.app.model.user.User;
import net.github.rtc.util.annotation.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Vladislav Pikus
 */
@Entity
@Validatable
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ForExport("Id")
    private long id;

    @Column
    @ForExport("Code")
    private String code;

    @Required
    @Minlength(2)
    @Maxlength(50)
    @Column
    @ForExport("Name")
    private String name;

    @Required
    @Column
    @ForExport("Category")
    @Enumerated(EnumType.STRING)
    private CourseType type;

    @Required
    @Temporal(TemporalType.TIMESTAMP)
    @ForExport("Start date")
    private Date startDate;

    @Required
    @Temporal(TemporalType.TIMESTAMP)
    @ForExport("End date")
    private Date endDate;

    @Column
    @ForExport("Status")
    @Enumerated(EnumType.STRING)
    private CourseStatus status = CourseStatus.DRAFT;

    @Column
    @ForExport("Publish date")
    private Date publishDate;

    @Maxlength(255)
    @Column
    @ForExport("Description")
    private String description;

    @Required
    @net.github.rtc.util.annotation.Number
    @Min(1)
    @Column
    @ForExport("Capacity")
    private Integer capacity = 10;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name="courses_tags",
            joinColumns={@JoinColumn(name="tagId")},
            inverseJoinColumns={@JoinColumn(name="id")})
    @ForExport("Tags")
    private List<Tag> tags;

    @Required
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name="courses_experts",
            joinColumns={@JoinColumn(name="expertId")},
            inverseJoinColumns={@JoinColumn(name="courseId")})
    @ForExport(value = "Experts")//, inculdeField = {"Author Email"})
    private Set<User> experts;



    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public Set<User> getExperts() {return experts;}

    public void setExperts(Set<User> experts) {this.experts = experts;}

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

    public CourseStatus getStatus() { return status; }

    public void setStatus(CourseStatus status) { this.status = status; }

    public Date getPublishDate() { return publishDate; }

    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public long getId() {return id; }

    public void setId(long id) { this.id = id; }

    public Course() {

    }

    public Course(String code, String name, CourseType type, Set<User> experts, Date startDate, Date endDate,
                  Date publishDate, Integer capacity, String description, CourseStatus status) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.experts = experts;
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
        sb.append(", experts=").append(experts);
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

        if (experts != null ? !experts.equals(course.experts) : course.experts != null) return false;
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
        result = 31 * result + (experts != null ? experts.hashCode() : 0);
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

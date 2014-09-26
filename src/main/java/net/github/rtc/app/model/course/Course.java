package net.github.rtc.app.model.course;

import net.github.rtc.app.model.user.User;
import net.github.rtc.util.annotation.ForExport;
import net.github.rtc.util.annotation.validation.*;
import net.github.rtc.util.annotation.validation.Number;
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

    public static final int HASH_CODE_CONSTANT = 31;
    public static final int PRIMARY_LENGTH = 50;
    public static final int DEFAULT_CAPACITY = 10;
    public static final int DESCRIPTION_LENGTH = 255;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ForExport("Id")
    private long id;

    @Column
    @ForExport("Code")
    private String code;

    @Required
    @Minlength(2)
    @Maxlength(PRIMARY_LENGTH)
    @Column
    @ForExport("Name")
    private String name;

    @Required

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "CourseTypes",
            joinColumns = @JoinColumn(name = "course_id"))
    @Enumerated(EnumType.STRING)
    @ForExport("Category")
    private Set<CourseType> types;

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

    @Maxlength(DESCRIPTION_LENGTH)
    @Column
    @ForExport("Description")
    private String description;

    @Required
    @Number
    @Min(1)
    @Column
    @ForExport("Capacity")
    private Integer capacity = DEFAULT_CAPACITY;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "courses_tags",
      joinColumns = { @JoinColumn(name = "tagId") },
      inverseJoinColumns = { @JoinColumn(name = "id") })
    @ForExport("Tags")
    private List<Tag> tags;

    @Required
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "courses_experts",
      joinColumns = { @JoinColumn(name = "expertId") },
      inverseJoinColumns = { @JoinColumn(name = "courseId") })
    @ForExport(value = "Experts")//, inculdeField = {"Author Email"})
    private Set<User> experts;

    public Course() {

    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }

    public Set<CourseType> getTypes() {
        return types;
    }

    public void setTypes(final Set<CourseType> types) {
        this.types = types;
    }

    public Set<User> getExperts() {
        return experts;
    }

    public void setExperts(final Set<User> experts) {
        this.experts = experts;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(final CourseStatus status) {
        this.status = status;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(final Date publishDate) {
        this.publishDate = publishDate;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Course{");
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", types=").append(types);
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        final Course course = (Course) o;

        if (experts != null ? !experts.equals(
          course.experts) : course.experts != null) {
            return false;
        }
        if (code != null ? !code.equals(course.code) : course.code != null) {
            return false;
        }
        if (endDate != null ? !endDate.equals(
          course.endDate) : course.endDate != null) {
            return false;
        }
        if (name != null ? !name.equals(course.name) : course.name != null) {
            return false;
        }
        if (startDate != null ? !startDate.equals(
          course.startDate) : course.startDate != null) {
            return false;
        }
        if (tags != null ? !tags.equals(course.tags) : course.tags != null) {
            return false;
        }
        if (types != null ? !types.equals(course.types) : course.types != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result
          = HASH_CODE_CONSTANT * result + (name != null ? name.hashCode() : 0);
        result
          = HASH_CODE_CONSTANT * result + (types != null ? types.hashCode() : 0);
        result
          = HASH_CODE_CONSTANT * result + (experts != null ? experts.hashCode(
        ) : 0);
        result
          = HASH_CODE_CONSTANT * result + (startDate != null ? startDate
          .hashCode() : 0);
        result
          = HASH_CODE_CONSTANT * result + (endDate != null ? endDate.hashCode(
        ) : 0);
        result
          = HASH_CODE_CONSTANT * result + (tags != null ? tags.hashCode() : 0);
        result
          = HASH_CODE_CONSTANT * result + (status != null ? status.hashCode()
          : 0);
        result
          = HASH_CODE_CONSTANT * result + (description != null ? description
          .hashCode() : 0);
        result
          = HASH_CODE_CONSTANT * result + (publishDate != null ? publishDate
          .hashCode() : 0);
        result
          = HASH_CODE_CONSTANT * result + (capacity != null ? capacity
          .hashCode() : 0);
        return result;
    }
}

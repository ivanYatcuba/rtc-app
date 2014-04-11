package net.github.rtc.app.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
@XmlRootElement
public class CourseDto {
    private Collection<Course> courses;
    private int totalCount;
    private int offset;
    private int limit;

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

package net.github.rtc.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
public class CourseDto {
    private Collection<Course> courses;
    private int totalCount;
    private int offset;
    private int limit;

    public CourseDto(Builder builder) {
        this.courses = builder.courses;
        this.totalCount = builder.totalCount;
        this.offset = builder.offset;
        this.limit = builder.limit;
    }

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

    /**
     * Builder for {@link CourseDto}. The builder simplifies the creation of objects
     * and makes the code more understandable
     */
    @JsonIgnoreType
    public static class Builder {
        private Collection<Course> courses = new ArrayList<Course>();
        private int totalCount;
        private int offset;
        private int limit;

        public Builder courses(Collection<Course> courses) {
            this.courses = courses;
            return this;
        }

        public Builder totalCount(int totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public CourseDto build() {
            return new CourseDto(this);
        }
    }
}

package net.github.rtc.app.utils;

import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.course.CourseType;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.date.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class NewsFromCourseBuilder {

    private static final String ILLEGAL_ARGUMENT = "Argument cannot be null";

    private static final String TITLE_TEMPLATE = "/templates/news/course/titleTemplate.ftl";
    private static final String MIX_COURSE_TEMPLATE = "/templates/news/course/MixCourse.ftl";
    private static final String BA_COURSE_TEMPLATE = "/templates/news/course/BaCourse.ftl";
    private static final String DEV_COURSE_TEMPLATE = "/templates/news/course/DevCourse.ftl";
    private static final String QA_COURSE_TEMPLATE = "/templates/news/course/QaCourse.ftl";

    @Autowired
    private StringFromTemplateBuilder stringFromTemplateBuilder;
    @Autowired
    private DateService dateService;

    private Course course;
    private User author;
    private Date creationDate;

    public NewsFromCourseBuilder setCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        this.course = course;
        return this;
    }

    public NewsFromCourseBuilder setAuthor(User author) {
        if (author == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        this.author = author;
        return this;
    }

    public NewsFromCourseBuilder setCreationDate(Date creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        this.creationDate = new Date(creationDate.getTime());
        return this;
    }

    public News build() {
        final News news = new News();
        final Map<String, Object> templateMap = new HashMap<>();
        templateMap.put("courseName", course.getName());
        news.setTitle(stringFromTemplateBuilder.setTemplate(TITLE_TEMPLATE).
                                                      setTemplateParams(templateMap).build());
        news.setDescription(getCourseDescription(course));
        news.setAuthor(author);
        news.setCreateDate(creationDate);
        return news;
    }

    /**
     * Build course description
     * @param course for what course
     * @return course description
     */
    private String getCourseDescription(final Course course) {
        final Map<String, Object> templateMap = new HashMap<>();
        templateMap.put("termInMonth", dateService.getMothPeriod(course.getStartDate(), course.getEndDate()));
        final String templatePath = getCourseDescriptionTemplatePath(course);
        return stringFromTemplateBuilder.setTemplate(templatePath).setTemplateParams(templateMap).build();
    }

    /**
     * Get path for course description template
     * @param course what course?
     * @return  path to template
     */
    private String getCourseDescriptionTemplatePath(final Course course) {
        if (course.getTypes().size() == 1) {
            final CourseType type = course.getTypes().iterator().next();
            switch (type) {
                case BA:
                    return BA_COURSE_TEMPLATE;
                case DEV:
                    return DEV_COURSE_TEMPLATE;
                case QA:
                    return QA_COURSE_TEMPLATE;
                default:
                    return "";
            }
        } else {
            return MIX_COURSE_TEMPLATE;
        }
    }


}

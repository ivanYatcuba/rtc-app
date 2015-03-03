package net.github.rtc.app.service.builder;

import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.course.CourseType;
import net.github.rtc.app.model.entity.course.Tag;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.security.AuthorizedUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Object that helps to build news from course
 */
@Component
public class NewsBuilder {

    private static final String TITLE_TEMPLATE = "/templates/news/course/titleTemplate.ftl";
    private static final String MIX_COURSE_TEMPLATE = "/templates/news/course/MixCourse.ftl";
    private static final String BA_COURSE_TEMPLATE = "/templates/news/course/BaCourse.ftl";
    private static final String DEV_COURSE_TEMPLATE = "/templates/news/course/DevCourse.ftl";
    private static final String QA_COURSE_TEMPLATE = "/templates/news/course/QaCourse.ftl";

    @Autowired
    private TemplateStringBuilder templateStringBuilder;

    @Autowired
    private DateService dateService;

    /**
     * Build news from course
     * @param course the course from what news needs to be built
     * @return the news based on course
     */
    public News build(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("course = null");
        }

        final News news = new News();
        final Map<String, Object> templateMap = new HashMap<>();

        final User author = AuthorizedUserProvider.getAuthorizedUser();
        final Date creationDate = dateService.getCurrentDate();

        templateMap.put("courseName", course.getName());
        news.setTitle(templateStringBuilder.build(TITLE_TEMPLATE, templateMap));

        news.setDescription(getCourseDescription(course));
        news.setAuthor(author);
        news.setCreateDate(creationDate);

        if (course.getTags() != null) {
            final List<Tag> newsTags = new ArrayList<>();
            for (Tag tag : course.getTags()) {
                newsTags.add(tag);
            }
            news.setTags(newsTags);
        }

        return news;
    }

    /**
     * Get description of the news depending on course
     * @param course the course that needs to be described
     * @return description in string
     */
    private String getCourseDescription(final Course course) {
        final Map<String, Object> templateMap = new HashMap<>();
        templateMap.put("termInMonth", dateService.getMothPeriod(course.getStartDate(), course.getEndDate()));
        final String templatePath = getCourseDescriptionTemplatePath(course);
        return templateStringBuilder.build(templatePath, templateMap);
    }

    /**
     * Detect what course description param should be used depending on course type
     * @param course course that needs description
     * @return path to template that contains needed description
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

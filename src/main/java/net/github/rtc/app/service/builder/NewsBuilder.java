package net.github.rtc.app.service.builder;

import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.course.CourseType;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.security.AuthorizedUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Date;
import java.util.Map;

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
        return news;
    }

    private String getCourseDescription(final Course course) {
        final Map<String, Object> templateMap = new HashMap<>();
        templateMap.put("termInMonth", dateService.getMothPeriod(course.getStartDate(), course.getEndDate()));
        final String templatePath = getCourseDescriptionTemplatePath(course);
        return templateStringBuilder.build(templatePath, templateMap);
    }

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

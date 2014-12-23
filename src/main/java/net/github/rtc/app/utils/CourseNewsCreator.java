package net.github.rtc.app.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseType;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.DateService;
import net.github.rtc.app.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CourseNewsCreator {

    private static final String TITLE_TEMPLATE = "/templates/news/course/titleTemplate.ftl";
    private static final String MIX_COURSE_TEMPLATE = "/templates/news/course/MixCourse.ftl";
    private static final String BA_COURSE_TEMPLATE = "/templates/news/course/BaCourse.ftl";
    private static final String DEV_COURSE_TEMPLATE = "/templates/news/course/DevCourse.ftl";
    private static final String QA_COURSE_TEMPLATE = "/templates/news/course/QaCourse.ftl";
    private static final String UNKNOWN_VALUE = "UNKNOWN VALUE";
    @Autowired
    private DateService dateService;
    @Autowired
    private NewsService newsService;

    public News createNews(final Course course, User author) {
        final News news = new News();
        final Map<String, Object> templateMap = new HashMap<>();
        templateMap.put("courseName", course.getName());
        news.setTitle(getStringFromTemplate(TITLE_TEMPLATE, templateMap));
        news.setDescription(getCourseDescription(course));
        news.setAuthor(author);
        news.setCreateDate(dateService.getCurrentDate());
        newsService.create(news);
        return news;
    }

    private String getStringFromTemplate(final String templatePath, final Map<String, Object> templateMap) {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(CourseNewsCreator.class, "/");
        try {
            final Template template = config.getTemplate(templatePath);
            final StringWriter writer = new StringWriter();
            template.process(templateMap, writer);
            return writer.toString();
        } catch (IOException | TemplateException e) {
            return UNKNOWN_VALUE;
        }
    }

    private String getCourseDescription(final Course course) {
        final Map<String, Object> templateMap = new HashMap<>();
        templateMap.put("termInMonth", dateService.getMothPeriod(course.getStartDate(), course.getEndDate()));
        final String templatePath = getCourseDescriptionTemplatePath(course);
        if (templatePath.equals(DEV_COURSE_TEMPLATE)) {
            templateMap.put("tags", course.getTags());
        }
        return getStringFromTemplate(templatePath, templateMap);
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

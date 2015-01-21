package net.github.rtc.app.service.course;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.GenericService;
import net.github.rtc.app.service.ModelService;

import java.util.List;

public interface CourseService extends ModelService<Course>, GenericService<Course> {

    /**
     * Set course status as published
     *
     * @param course what course?
     */
    void publish(Course course);

    List<Course> startingSoonCourses();

    List<Course> findAllPublished();

    /**
     *
     * @param course create news about the course
     * @param author user that will be displayed as author of the news
     */
    void createNews(Course course, User author);

}

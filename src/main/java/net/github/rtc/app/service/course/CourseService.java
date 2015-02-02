package net.github.rtc.app.service.course;

import net.github.rtc.app.dto.user.UserCourseDTO;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.GenericService;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;

import java.util.List;

public interface CourseService extends ModelService<Course>, GenericService<Course> {

    /**
     * Set course status as published
     *
     * @param course what course?
     */
    void publish(Course course);

    void archive(String courseCode);

    SearchResults<UserCourseDTO> searchCoursesForUser(boolean withArchived, CourseSearchFilter filter);

    List<Course> startingSoonCourses();

    List<Course> findAllPublished();

    /**
     *
     * @param course create news about the course
     * @param author user that will be displayed as author of the news
     */
    void createNews(Course course, User author);

}

package net.github.rtc.app.service.course;

import net.github.rtc.app.dto.user.UserCourseDTO;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.GenericService;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;

public interface CourseService extends ModelService<Course>, GenericService<Course> {

    void publish(final boolean ifCreateNews, String courseCode);

    void archive(String courseCode);

    SearchResults<UserCourseDTO> searchCoursesForUser(boolean withArchived, CourseSearchFilter filter);

    void saveCourse(final boolean ifPublish, final boolean ifCreateNews, Course course, boolean doUpdate);

    void createNews(Course course, User author);
}

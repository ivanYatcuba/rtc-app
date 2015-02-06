package net.github.rtc.app.service.course;

import net.github.rtc.app.model.dto.user.UserCourseDTO;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.service.GenericService;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.utils.datatable.search.CourseSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;

public interface CourseService extends ModelService<Course>, GenericService<Course> {

    void publish(final boolean isNewsCreated, String courseCode);

    void archive(String courseCode);

    SearchResults<UserCourseDTO> searchCoursesForUser(boolean withArchived, CourseSearchFilter filter);

    void create(final boolean isPublished, final boolean newsCreated, Course course);
    void update(final boolean isPublished, final boolean newsCreated, Course course);


}

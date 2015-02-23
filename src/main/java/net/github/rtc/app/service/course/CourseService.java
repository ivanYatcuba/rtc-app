package net.github.rtc.app.service.course;

import net.github.rtc.app.model.dto.user.UserCourseDTO;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.service.generic.GenericService;
import net.github.rtc.app.service.generic.ModelService;
import net.github.rtc.app.model.dto.filter.CourseSearchFilter;
import net.github.rtc.app.model.dto.SearchResults;

public interface CourseService extends ModelService<Course>, GenericService<Course> {

    void publish(String courseCode, final boolean isNewsCreated);

    void archive(String courseCode);

    SearchResults<UserCourseDTO> searchCoursesForUser(CourseSearchFilter filter);

    UserCourseDTO getUserCourseDTObyCode(String code);

    void create(final boolean isPublished, final boolean newsCreated, Course course);
    void update(final boolean isPublished, final boolean newsCreated, Course course);

    void addParticipant(String courseCode, String userCode);
    void deleteParticipant(String courseCode, String userCode);
}

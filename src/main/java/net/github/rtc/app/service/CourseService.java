package net.github.rtc.app.service;

import net.github.rtc.app.model.course.Course;

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

}

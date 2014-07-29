package net.github.rtc.app.export;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Ivan Yatcuba on 7/29/14.
 */
@Component
public class CourseExportTask {
    @Autowired
    private CoursesService coursesService;
    @Value("${course.export.path}")
    private String filePath;



    public void exportData() {
        XLSXReportBuilder reportBuilder = new XLSXReportBuilder();
        reportBuilder.build(Course.class, coursesService.findAll(), "courses", filePath);
    }
}

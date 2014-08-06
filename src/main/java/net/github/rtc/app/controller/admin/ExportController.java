package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.user.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chernichenko Bogdan on 15.05.2014.
 */

@Controller("exportController")
@RequestMapping("admin/export")
public class ExportController {

    //@Autowired
    //private SchedulerFactoryBean scheduler;

    private static final int BUFFER_SIZE = 4096;
    private static final String ROOT = "portal/admin";

    @Value("${user.export.path}")
    private String usersExportFile;
    @Value("${course.export.path}")
    private String coursesExportFile;


    @RequestMapping(value = "/exportCourses", method = RequestMethod.GET)
    public ModelAndView exportCourses() {
        ModelAndView mav = new ModelAndView(ROOT + "/page/exportCourses");
        return mav;
    }

    @RequestMapping(value = "/exportUsers", method = RequestMethod.GET)
    public ModelAndView exportUser() {
        ModelAndView mav = new ModelAndView(ROOT + "/page/exportUsers");
        return mav;
    }

    @RequestMapping(value = "/downloadUserExport", method = RequestMethod.GET)
    public void downloadUserExport(HttpServletResponse response) throws IOException {
        downloadFile(usersExportFile, response);
    }

    @RequestMapping(value = "/downloadCourseExport", method = RequestMethod.GET)
    public void downloadCourseExport(HttpServletResponse response) throws IOException {
        downloadFile(coursesExportFile, response);
    }

    private void downloadFile(String filename, HttpServletResponse response) throws IOException{
        File downloadFile = new File(filename);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        response.setContentType(Files.probeContentType(downloadFile.toPath()));
        response.setContentLength((int) downloadFile.length());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }

    @ModelAttribute("roles")
    public List<RoleType> getCategories() {

        List<RoleType> roles = new ArrayList<>();
        roles.add(RoleType.ROLE_USER);
        roles.add(RoleType.ROLE_ADMIN);
        roles.add(RoleType.ROLE_EXPERT);

        return roles;
    }

    @ModelAttribute("stats")
    public List<String> getStats() {

        List<String> stats = new ArrayList<>();
        stats.add(CourseStatus.DRAFT.name());
        stats.add(CourseStatus.PUBLISHED.name());
        return stats;
    }

}

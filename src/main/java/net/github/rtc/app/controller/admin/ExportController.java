package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.report.ExportFormat;
import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.ReportService;
import net.github.rtc.app.utils.ExportFieldExtractor;
import net.github.rtc.util.converter.ValidationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Chernichenko Bogdan on 15.05.2014.
 */

@Controller("exportController")
@RequestMapping("admin/export")
public class ExportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private ValidationContext validationContext;

    private static final int BUFFER_SIZE = 4096;
    private static final String ROOT = "portal/admin";
    private static final String ROOT_MODEL = "export";

    @Value("${report.export.path}")
    private String exportPath;

    @RequestMapping(value = "/viewAll", method = RequestMethod.GET)
    public ModelAndView exportCourses() {
        ModelAndView mav = new ModelAndView(ROOT + "/page/reportList");
        mav.addObject("reports", reportService.getAll());
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView openCreatePage() {
        Set<String> formatLables = getTypes().keySet();
        ModelAndView mav = new ModelAndView("portal/admin/page/reportCreate");
        mav.addObject("types", formatLables);
        mav.addObject("validationRules", validationContext.get(ReportDetails.class));
        return mav;
    }

    @RequestMapping(value = "/update/{reportCode}", method = RequestMethod.GET)
    public ModelAndView openUpdatePage(@PathVariable String reportCode) {
        ModelAndView mav = new ModelAndView("portal/admin/page/reportEdit");
        Set<String> formatLables = getTypes().keySet();
        ReportDetails reportDetails = reportService.findReportByCode(reportCode);
        mav.addObject("report", reportDetails);
        mav.addObject("types", formatLables);
        mav.addObject("validationRules", validationContext.get(ReportDetails.class));
        return mav;
    }

    @RequestMapping(value = "/{reportCode}", method = RequestMethod.GET)
    public ModelAndView viewReport(@PathVariable String reportCode) {
        ModelAndView mav = new ModelAndView("portal/admin/page/reportDetails");
        mav.addObject("report", reportService.findReportByCode(reportCode));
        return mav;
    }

    @RequestMapping(value = "/delete/{reportCode}", method = RequestMethod.GET)
    public String deleteReport(@PathVariable String reportCode) {
        reportService.delete(reportService.findReportByCode(reportCode));
        return "redirect:/admin/export/viewAll";
    }

    @RequestMapping(value = "/insertReport", method = RequestMethod.POST)
    public @ResponseBody  ModelAndView createHandler(@ModelAttribute("report") ReportDetails report,
                                 @RequestParam String selectedType,
                                 @RequestParam(value = "reportFields", required = false) List<String> reportFields) {
        report.setExportClass(getTypes().get(selectedType));
        report.setFields(reportFields);
        reportService.insert(report);
        return new ModelAndView("redirect:/admin/export/"+report.getCode());
    }

    @RequestMapping(value = "/updateReport", method = RequestMethod.POST)
    public @ResponseBody  ModelAndView editHandler(@ModelAttribute("report") ReportDetails report,
                                                     @RequestParam String selectedType,
                                                     @RequestParam(value = "reportFields", required = false) List<String> reportFields) {
        report.setExportClass(getTypes().get(selectedType));
        report.setFields(reportFields);
        reportService.update(report);
        return new ModelAndView("redirect:/admin/export/"+report.getCode());
    }

    @RequestMapping(value = "/getFields", method = RequestMethod.GET)
    public @ResponseBody List<String> getFields(@RequestParam String selectedType) {
        return  getClassFields(getTypes().get(selectedType));
    }


    @RequestMapping(value = "/download/{reportCode}", method = RequestMethod.GET)
    public void downloadUserExport(HttpServletResponse response, @PathVariable String reportCode) throws IOException {
        ReportDetails reportDetails = reportService.findReportByCode(reportCode);
        StringBuilder filePath = new StringBuilder(exportPath).append("/").append(reportCode).append(".").
                append(reportDetails.getExportFormat().toString().toLowerCase());
        File downloadFile = new File(filePath.toString());
        FileInputStream inputStream = new FileInputStream(downloadFile);

        response.setContentType(Files.probeContentType(downloadFile.toPath()));
        response.setContentLength((int) downloadFile.length());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", reportDetails.getName());
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

    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @ModelAttribute("formats")
    public List<String> getFormats() {
        return ExportFormat.findAll();
    }

    public  Map<String, Class> getTypes(){
        Map<String, Class> types = new HashMap<>();
        types.put("User", User.class);
        types.put("Course", Course.class);
        return types;
    }

    public List<String> getClassFields(Class aClass){
        return ExportFieldExtractor.getAviableFieldList(aClass);
    }

    @ModelAttribute("stats")
    public List<String> getStats() {

        List<String> stats = new ArrayList<>();
        stats.add(CourseStatus.DRAFT.name());
        stats.add(CourseStatus.PUBLISHED.name());
        return stats;
    }

    @ModelAttribute("report")
    public ReportDetails getCommandObject() {
        return new ReportDetails();
    }

    @InitBinder("report")
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}

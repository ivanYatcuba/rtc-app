package net.github.rtc.app.controller.admin;

import net.github.rtc.app.exception.ServiceProcessingException;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.course.CourseStatus;
import net.github.rtc.app.model.report.ExportFormat;
import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.ReportService;
import net.github.rtc.app.utils.ExportFieldExtractor;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import net.github.rtc.util.converter.ValidationContext;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Chernichenko Bogdan on 15.05.2014.
 */

@Controller("exportController")
@RequestMapping("admin/export")
public class ExportController {

    private static final int REPORTS_PER_PAGE = 10;
    private static final int BUFFER_SIZE = 4096;
    private static final String ROOT = "portal/admin";
    private static final String STRING_TYPES = "types";
    private static final String STRING_VALIDATION_RULES = "validationRules";
    private static final String STRING_REPORT = "report";
    private static final String REDIRECT_EXPORT = "redirect:/admin/export/";
    private static final String PAGE_REPORT_LIST = "/page/reportList";
    private static Logger log = LoggerFactory.getLogger(ExportController.class.getName());

    @Autowired
    private ReportService reportService;
    @Autowired
    private ValidationContext validationContext;

    @Value("${report.export.path}")
    private String exportPath;

    @RequestMapping(value = "/viewAll", method = RequestMethod.GET)
    public ModelAndView exportCourses() {
        return viewAll(1);
    }


    @RequestMapping(value = "/viewAll/{numberOfPage}",
      method = RequestMethod.POST)
    public ModelAndView viewAll(@PathVariable final int numberOfPage) {

        final ModelAndView mav = new ModelAndView(ROOT
          + PAGE_REPORT_LIST);
        final SearchResults<ReportDetails> results
          = reportService.search(DetachedCriteria.forClass(ReportDetails.class),
          numberOfPage, REPORTS_PER_PAGE);
        mav.addAllObjects(results.getPageModel(REPORTS_PER_PAGE, numberOfPage));

        mav.addObject("reports", results.getResults());
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView openCreatePage() {
        final Set<String> formatLables = getTypes().keySet();
        final ModelAndView mav = new ModelAndView(
          "portal/admin/page/reportCreate");
        mav.addObject(STRING_TYPES, formatLables);
        mav.addObject(STRING_VALIDATION_RULES,
          validationContext.get(ReportDetails.class));
        return mav;
    }

    @RequestMapping(value = "/update/{reportCode}", method = RequestMethod.GET)
    public ModelAndView openUpdatePage(@PathVariable final String reportCode) {
        final ModelAndView mav = new ModelAndView(
          "portal/admin/page/reportEdit");
        final Set<String> formatLables = getTypes().keySet();
        final ReportDetails reportDetails = reportService.findReportByCode(
          reportCode);
        mav.addObject(STRING_REPORT, reportDetails);
        mav.addObject(STRING_TYPES, formatLables);
        mav.addObject(STRING_VALIDATION_RULES,
          validationContext.get(ReportDetails
            .class));
        return mav;
    }

    @RequestMapping(value = "/{reportCode}", method = RequestMethod.GET)
    public ModelAndView viewReport(@PathVariable final String reportCode) {
        final ModelAndView mav = new ModelAndView(
          "portal/admin/page/reportDetails");
        mav.addObject(STRING_REPORT,
          reportService.findReportByCode(reportCode));
        return mav;
    }

    @RequestMapping(value = "/delete/{reportCode}", method = RequestMethod.GET)
    public String deleteReport(@PathVariable final String reportCode) {
        reportService.delete(reportService.findReportByCode(reportCode));
        return "redirect:/admin/export/viewAll";
    }

    @RequestMapping(value = "/insertReport", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView createHandler(
      @ModelAttribute("report") final ReportDetails report,
      @RequestParam final String selectedType,
      @RequestParam(value = "reportFields",
        required = false) final List<String> reportFields) {
        report.setExportClass(getTypes().get(selectedType));
        report.setFields(reportFields);
        reportService.insert(report);
        return new ModelAndView(REDIRECT_EXPORT + report.getCode());
    }

    @RequestMapping(value = "/updateReport", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView editHandler(
      @ModelAttribute("report") final ReportDetails report,
      @RequestParam final String selectedType,
      @RequestParam(value = "reportFields",
        required = false) final List<String> reportFields) {
        report.setExportClass(getTypes().get(selectedType));
        report.setFields(reportFields);
        reportService.update(report);
        return new ModelAndView(REDIRECT_EXPORT + report.getCode());
    }

    @RequestMapping(value = "/getFields", method = RequestMethod.GET)
    public
    @ResponseBody
    List<String> getFields(@RequestParam final String selectedType) {
        return getClassFields(getTypes().get(selectedType));
    }


    @RequestMapping(value = "/download/{reportCode}",
      method = RequestMethod.GET)
    public void downloadUserExport(
      final HttpServletResponse response,
      @PathVariable final String reportCode){
        try {
            final ReportDetails reportDetails = reportService.findReportByCode(
                    reportCode);
            final StringBuilder filePath = new StringBuilder(exportPath).append(
                    "/").append(reportCode).append(".").
                    append(reportDetails.getExportFormat().toString().toLowerCase());
            final File downloadFile = new File(filePath.toString());
            final FileInputStream inputStream = new FileInputStream(downloadFile);

            response.setContentType(Files.probeContentType(downloadFile.toPath()));
            response.setContentLength((int) downloadFile.length());

            final String headerKey = "Content-Disposition";
            final String headerValue = String.format(
                    "attachment; " + "filename=\"%s\"", reportDetails.getName());
            response.setHeader(headerKey, headerValue);
            final OutputStream outStream = response.getOutputStream();
            final byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);

            }
            inputStream.close();
            outStream.close();
        }
        catch(ServiceProcessingException e){log.error("Catching: ", e);}
        catch(IOException e){log.error("Catching: ", e);}
    }

    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @ModelAttribute("formats")
    public List<String> getFormats() {
        return ExportFormat.findAll();
    }

    public Map<String, Class> getTypes() {
        final Map<String, Class> types = new HashMap<>();
        types.put("User", User.class);
        types.put("Course", Course.class);
        return types;
    }

    public List<String> getClassFields(final Class aClass) {
        return ExportFieldExtractor.getAviableFieldList(aClass);
    }

    @ModelAttribute("stats")
    public List<String> getStats() {

        final List<String> stats = new ArrayList<>();
        stats.add(CourseStatus.DRAFT.name());
        stats.add(CourseStatus.PUBLISHED.name());
        return stats;
    }

    @ModelAttribute("report")
    public ReportDetails getCommandObject() {
        return new ReportDetails();
    }

    @InitBinder("report")
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class,
          new CustomDateEditor(dateFormat, true));
    }

}

package net.github.rtc.app.controller.admin;

import net.github.rtc.app.controller.common.MenuItem;
import net.github.rtc.app.model.report.ExportFormat;
import net.github.rtc.app.model.report.ReportClasses;
import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.service.report.ReportService;
import net.github.rtc.app.utils.ExportFieldExtractor;
import net.github.rtc.app.utils.enums.EnumOperation;
import net.github.rtc.util.converter.ValidationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("admin/export")
public class ExportController implements MenuItem {

    private static Logger log = LoggerFactory.getLogger(ExportController.class.getName());

    private static final String TYPES = "types";
    private static final String REPORT = "report";
    private static final String FORMATS = "formats";
    private static final String VALIDATION_RULES = "validationRules";
    private static final String HEADER_KEY = "Content-Disposition";

    private static final String ROOT = "portal/admin";
    private static final String UPDATE_VIEW = "/report/reportUpdate";
    private static final String CREATE_VIEW = "/report/reportCreate";
    private static final String DETAILS_VIEW = "/report/reportDetails";
    private static final String REDIRECT_EXPORT = "redirect:/admin/export/";

    @Autowired
    private ReportService reportService;
    @Autowired
    private ValidationContext validationContext;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView openCreatePage() {
        final ModelAndView mav = new ModelAndView(ROOT + CREATE_VIEW);
        mav.addObject(VALIDATION_RULES, validationContext.get(ReportDetails.class));
        return mav;
    }

    @RequestMapping(value = "/insertReport", method = RequestMethod.POST)
    public String createReport(@ModelAttribute("report") final ReportDetails report) {
        reportService.create(report);
        return REDIRECT_EXPORT + report.getCode();
    }

    @RequestMapping(value = "/{reportCode}", method = RequestMethod.GET)
    public ModelAndView viewReport(@PathVariable final String reportCode) {
        final ModelAndView mav = new ModelAndView(ROOT + DETAILS_VIEW);
        mav.addObject(REPORT, reportService.findByCode(reportCode));
        return mav;
    }

    @RequestMapping(value = "/update/{reportCode}", method = RequestMethod.GET)
    public ModelAndView openUpdatePage(@PathVariable final String reportCode) {
        final ModelAndView mav = new ModelAndView(ROOT + UPDATE_VIEW);
        mav.addObject(REPORT, reportService.findByCode(reportCode));
        mav.addObject(VALIDATION_RULES, validationContext.get(ReportDetails.class));
        return mav;
    }

    @RequestMapping(value = "/updateReport", method = RequestMethod.POST)
    public String editReport(@ModelAttribute("report") final ReportDetails report) {
        reportService.update(report);
        return REDIRECT_EXPORT + report.getCode();
    }

    @RequestMapping(value = "/delete/{reportCode}", method = RequestMethod.GET)
    public String deleteReport(@PathVariable final String reportCode) {
        reportService.deleteByCode(reportCode);
        return "redirect:/admin/search";
    }

    @RequestMapping(value = "/getFields", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getFields(@RequestParam final String selectedType) {
        return ExportFieldExtractor.getAvailableFieldList(ReportClasses.valueOf(selectedType).getValue());
    }

    @RequestMapping(value = "/download/{reportCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public void downloadUserExport(@PathVariable final String reportCode,  final HttpServletResponse response) {
        final ReportDetails reportDetails = reportService.findByCode(reportCode);
        final File downloadFile = reportService.getReport(reportDetails);
        response.setHeader(HEADER_KEY, String.format("attachment; " + "filename=\"%s\"", reportDetails.getName()));
        try (final InputStream is = new FileInputStream(downloadFile)) {
            response.setContentType(Files.probeContentType(downloadFile.toPath()));
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            log.info("Error writing file to output stream. Filename was '{}'", reportCode, ex);
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @ModelAttribute(REPORT)
    public ReportDetails getCommandObject() {
        return new ReportDetails();
    }

    @ModelAttribute(FORMATS)
    public List<String> getFormats() {
        return EnumOperation.findAllName(ExportFormat.class);
    }

    @ModelAttribute(TYPES)
    public ReportClasses[] getTypes() {
        return ReportClasses.values();
    }

    @Override
    public String getMenuItem() {
        return REPORT;
    }
}

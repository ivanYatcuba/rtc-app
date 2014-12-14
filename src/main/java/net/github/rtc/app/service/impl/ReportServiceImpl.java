package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dao.impl.ReportDao;
import net.github.rtc.app.export.ReportBuilder;
import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.service.CodeGenerationService;
import net.github.rtc.app.service.DateService;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl extends AbstractGenericServiceImpl<ReportDetails> implements ReportService {

    private static final String STRING_REPORT = "Report: ";
    private static final String DOT = ".";
    private static Logger log = LoggerFactory.getLogger(ReportServiceImpl.class.getName());
    @Autowired
    private ReportDao reportDao;
    @Autowired
    private DateService dateService;
    @Autowired
    private CodeGenerationService codeGenerationService;
    @Resource(name = "serviceHolder")
    private Map<Class, ? extends ModelService> serviceHolder;
    @Value("${report.export.path}")
    private String exportPath;

    @Override
    protected GenericDao<ReportDetails> getDao() {
        return reportDao;
    }

    @Override
    @Transactional
    public ReportDetails create(final ReportDetails report) {
        log.info("Creating report: " + report);
        report.setCode(codeGenerationService.generate());
        report.setCreatedDate(dateService.getCurrentDate());
        try {
            compileReport(report);
            final ReportDetails result = reportDao.create(report);
            log.info(STRING_REPORT + report.getCode() + " created successfully!");
            return result;
        } catch (final Exception e) {
            log.info("Report creation failed: " + report.getCode());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public ReportDetails update(final ReportDetails report) {
        log.info("Updating report: " + report);
        try {
            compileReport(report);
            reportDao.update(report);
            log.info(STRING_REPORT + report.getCode() + " updated successfully!");
            return report;
        } catch (final Exception e) {
            log.info("Report update failed: " + report.getCode());
            e.printStackTrace();
            return null;
        }
    }

    public ReportDetails compileReport(ReportDetails report) {
        final String filePath = exportPath + report.getCode() + DOT + report.getExportFormat().toString().toLowerCase();
        final ModelService service = serviceHolder.get(report.getExportClass());
        final List<?> objects = service.findAll();
        try {
            ReportBuilder.build(report, objects, filePath);
            return report;
        } catch (final NoSuchFieldException e) {
            log.info("Report building failed: " + report.getCode());
            return null;
        }
    }

    @Override
    public File getReport(ReportDetails details) {
        final ReportDetails reportDetails = findByCode(details.getCode());
        final String filePath = new StringBuilder(exportPath).append("/").append(details.getCode()).append(DOT).
                append(reportDetails.getExportFormat().toString().toLowerCase()).toString();
        return new File(filePath);
    }
}

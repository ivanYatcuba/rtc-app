package net.github.rtc.app.service.export;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.dao.export.ExpertDao;
import net.github.rtc.app.model.entity.report.ReportDetails;
import net.github.rtc.app.service.generic.CodeGenerationService;
import net.github.rtc.app.service.generic.ModelService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.generic.AbstractGenericServiceImpl;
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
public class ExportServiceImpl extends AbstractGenericServiceImpl<ReportDetails> implements ExportService {

    private static final String REPORT = "Report: ";
    private static final String DOT = ".";
    private static Logger log = LoggerFactory.getLogger(ExportServiceImpl.class.getName());
    @Autowired
    private ExpertDao expertDao;
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
        return expertDao;
    }

    @Override
    @Transactional
    public ReportDetails create(final ReportDetails report) {
        log.info("Creating report: " + report);
        report.setCode(codeGenerationService.generate());
        report.setCreatedDate(dateService.getCurrentDate());
        try {
            compileReport(report);
            final ReportDetails result = expertDao.create(report);
            log.info(REPORT + report.getCode() + " created successfully!");
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
            expertDao.update(report);
            log.info(REPORT + report.getCode() + " updated successfully!");
            return report;
        } catch (final Exception e) {
            log.info("Report update failed: " + report.getCode());
            e.printStackTrace();
            return null;
        }
    }

    public ReportDetails compileReport(ReportDetails report) {
        final String filePath = exportPath + report.getCode() + DOT + report.getExportFormat().toString().toLowerCase();
        final ModelService service = serviceHolder.get(report.getExportClass().getValue());
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

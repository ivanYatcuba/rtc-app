package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.impl.ReportDao;
import net.github.rtc.app.export.ReportBuilder;
import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.service.ReportService;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Ivan Yatcuba on 8/16/14.
 * Refactored by Vasiliy Sobol on 1/10/14
 */

@Service
public class ReportServiceImpl implements ReportService {

    private static final String STRING_REPORT = "Report: ";
    private static Logger log = LoggerFactory.getLogger(ReportServiceImpl
            .class.getName());

    @Autowired
    private ReportDao reportResource;

    @Resource(name = "serviceHolder")
    private Map<Class, ? extends ModelService> serviceHolder;

    @Value("${report.export.path}")
    private String exportPath;

    @Override
    @Transactional
    public void insert(final ReportDetails report) {
        log.info("Creating report: " + report);
        report.setCode(UUID.randomUUID().toString());
        report.setCreatedDate(new Date());

        try {
            compileReport(report);
            reportResource.create(report);
            log.info(
                    STRING_REPORT + report.getCode() + " created successfully!");
        } catch (final Exception e) {
            log.info("Report creation failed: " + report.getCode());
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public ReportDetails findReportByCode(final String code) {
        log.info("Getting report with code: " + code);
        return reportResource.findByCode(code);
    }

    @Override
    @Transactional
    public List<ReportDetails> getAll() {
        log.info("Getting all reports from database...");
        return (List) reportResource.findAll();
    }

    @Override
    @Transactional
    public void update(final ReportDetails report) {
        log.info("Updating report: " + report);
        try {
            reportResource.update(report);
            compileReport(report);
            log.info(
                    STRING_REPORT + report.getCode() + " updated successfully!");
        } catch (final Exception e) {
            log.info("Report update failed: " + report.getCode());
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void delete(final ReportDetails report) {
        log.info("Removing report: " + report);
        try {
            reportResource.deleteByCode(report.getCode());
            log.info(
                    STRING_REPORT + report.getCode() + " removed successfully!");
        } catch (final Exception e) {
            log.info("Report removal failed: " + report.getCode());
            e.printStackTrace();
        }

    }

    public void compileReport(ReportDetails report) {
        final String filePath = exportPath + report.getCode() + "." + report.getExportFormat().toString().toLowerCase();
        final ModelService service = serviceHolder.get(report.getExportClass());
        final List<?> objects = service.findAll();
        try {
            ReportBuilder.build(report, objects, filePath);
        } catch (final NoSuchFieldException e) {
            log.info("Report building failed: " + report.getCode());
        }
    }

    @Override
    @Transactional
    public SearchResults<ReportDetails> search(
            final DetachedCriteria criteria, final int start, final int max) {
        return reportResource.search(criteria, start, max);
    }
}

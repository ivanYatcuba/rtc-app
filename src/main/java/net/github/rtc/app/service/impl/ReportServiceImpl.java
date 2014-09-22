package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.impl.ReportDao;
import net.github.rtc.app.export.JobManager;
import net.github.rtc.app.export.JobManagerAction;
import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.service.ReportService;
import net.github.rtc.app.utils.datatable.SearchResults;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ivan Yatcuba on 8/16/14.
 */
@Service
public class ReportServiceImpl implements ReportService {

    private static final String STRING_REPORT = "Report: ";
    private static Logger log = LoggerFactory.getLogger(ReportServiceImpl
      .class.getName());
    @Autowired
    private ReportDao reportResource;
    @Autowired
    private JobManager jobManager;

    @Override
    @Transactional
    public void insert(final ReportDetails report) {
        log.info("Creating report: " + report);
        report.setCode(UUID.randomUUID().toString());
        report.setCreatedDate(new Date());
        try {
            jobManager.manageJob(report, JobManagerAction.CREATE);
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
            jobManager.manageJob(report, JobManagerAction.UPDATE);
            reportResource.update(report);
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
            jobManager.manageJob(report, JobManagerAction.DELETE);
            reportResource.deleteByCode(report.getCode());
            log.info(
              STRING_REPORT + report.getCode() + " removed successfully!");
        } catch (final Exception e) {
            log.info("Report removal failed: " + report.getCode());
            e.printStackTrace();
        }

    }

    @Override
    @Transactional
    public SearchResults<ReportDetails> search(
      final DetachedCriteria criteria, final int start, final int max) {
        return reportResource.search(criteria, start, max);
    }
}

package net.github.rtc.app.service.impl;

import net.github.rtc.app.export.JobManager;
import net.github.rtc.app.export.JobManagerAction;
import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.dao.impl.ReportDao;
import net.github.rtc.app.service.ReportService;
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

    @Autowired
    private ReportDao reportResource;
    @Autowired
    private JobManager jobManager;

    private static Logger LOG = LoggerFactory.getLogger(ReportServiceImpl.class.getName());

    @Override
    @Transactional
    public void insert(ReportDetails report) {
        LOG.info("Creating report: " + report);
        report.setCode(UUID.randomUUID().toString());
        report.setCreatedDate(new Date());
        try {
            jobManager.manageJob(report, JobManagerAction.CREATE);
            reportResource.create(report);
            LOG.info("Report: " + report.getCode() + " created successfully!");
        } catch (Exception e) {
            LOG.info("Report creation failed: " + report.getCode());
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public ReportDetails findReportByCode(String code) {
        LOG.info("Getting report with code: " + code);
        return reportResource.findByCode(code);
    }

    @Override
    @Transactional
    public List<ReportDetails> getAll() {
        LOG.info("Getting all reports from database...");
        return (List)reportResource.findAll();
    }

    @Override
    @Transactional
    public void update(ReportDetails report) {
        LOG.info("Updating report: " + report);
        try {
            jobManager.manageJob(report, JobManagerAction.UPDATE);
            reportResource.update(report);
            LOG.info("Report: " + report.getCode() + " updated successfully!");
        } catch (Exception e) {
            LOG.info("Report update failed: " + report.getCode());
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void delete(ReportDetails report) {
        LOG.info("Removing report: " + report);
        try {
            jobManager.manageJob(report, JobManagerAction.DELETE);
            reportResource.deleteByCode(report.getCode());
            LOG.info("Report: " + report.getCode() + " removed successfully!");
        } catch (Exception e) {
            LOG.info("Report removal failed: " + report.getCode());
            e.printStackTrace();
        }

    }
}

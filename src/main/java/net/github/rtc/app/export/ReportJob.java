package net.github.rtc.app.export;


import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.service.ModelService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Created by Ivan Yatcuba on 8/5/14.
 */
public class ReportJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(ReportJob.class.getName());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        ReportDetails reportDetails = (ReportDetails) context.getMergedJobDataMap().get("report");
        ModelService modelService = (ModelService) context.getMergedJobDataMap().get("modelService");
        String filePath = (String) context.getMergedJobDataMap().get("filePath");
        actualExecute(reportDetails, modelService, filePath);
    }

    public void runOutOfContext(ReportDetails reportDetails, ModelService modelService, String filePath) {
        actualExecute(reportDetails, modelService, filePath);
    }

    private void actualExecute(ReportDetails reportDetails, ModelService modelService, String filePath) {
        ReportBuilder reportBuilder = new ReportBuilder();
        try {
            reportBuilder.build(reportDetails.getFieldsFromClass(), modelService.findAll(),
                    reportDetails.getName(), filePath, reportDetails.getExportFormat());
            LOG.info("Job for report: " + reportDetails.getCode() + " completed!");
        } catch (NoSuchFieldException e) {
            LOG.info("Job for report: " + reportDetails.getCode() + " failed!");
        }
    }
}

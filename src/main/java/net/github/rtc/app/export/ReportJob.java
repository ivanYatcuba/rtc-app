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

    private static final String STRING_REPORT_JOB = "Job for report: ";

    private static final Logger LOG = LoggerFactory.getLogger(
      ReportJob.class.getName());

    @Override
    public void execute(final JobExecutionContext context) throws
      JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        final ReportDetails reportDetails
          = (ReportDetails) context.getMergedJobDataMap().get("report");
        final ModelService modelService
          = (ModelService) context.getMergedJobDataMap().get("modelService");
        final String filePath = (String) context.getMergedJobDataMap().get(
          "filePath");
        actualExecute(reportDetails, modelService, filePath);
    }

    public void runOutOfContext(
      final ReportDetails reportDetails,
      final ModelService modelService,
      final String filePath) {
        actualExecute(reportDetails, modelService, filePath);
    }

    private void actualExecute(
      final ReportDetails reportDetails,
      final ModelService modelService,
      final String filePath) {
        final ReportBuilder reportBuilder = new ReportBuilder();
        try {
            reportBuilder.build(reportDetails.getFieldsFromClass(),
              modelService.findAll(), reportDetails.getName(), filePath,
              reportDetails.getExportFormat());
            LOG.info(STRING_REPORT_JOB
                    + reportDetails.getCode()
                    + " "
                    +
                    "completed!");
        } catch (final NoSuchFieldException e) {
            LOG.info(STRING_REPORT_JOB
                    + reportDetails.getCode()
                    + " failed!");
        }
    }
}

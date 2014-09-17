package net.github.rtc.app.export;

import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.service.ModelService;
import org.quartz.JobDataMap;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.io.File;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Ivan Yatcuba on 8/18/14.
 */
@Component
public class JobManager {
    @Autowired
    private SchedulerFactoryBean scheduler;
    @Autowired
    private ApplicationContext applicationContext;

    @Value("${report.export.path}")
    private String exportPath;
    @Value("${report.export.crone}")
    private String croneExpression;


    public void manageJob(
      final ReportDetails report,
      final JobManagerAction managerAction) throws Exception {
        final ModelService modelService = getModelService(
          report.getExportClass());
        if (modelService
          == null) {
            throw new Exception("Service class of type"
              + report.getExportClass()
              + " not "
              +
              "found!");
        }
        final String filePath = exportPath
          + report.getCode()
          + "."
          + report.getExportFormat().toString().toLowerCase();
        if (managerAction
          == JobManagerAction.UPDATE
          || managerAction
          == JobManagerAction.DELETE) {
            scheduler.getObject().unscheduleJob(
              TriggerKey.triggerKey(report.getCode()));
            final File file = new File(filePath);
            file.delete();
        }
        if (managerAction
          == JobManagerAction.CREATE
          || managerAction
          == JobManagerAction.UPDATE) {
            final ReportJob reportJob = new ReportJob();
            final JobDataMap dataMap = new JobDataMap();
            dataMap.put("filePath", filePath);
            dataMap.put("modelService", modelService);
            dataMap.put("report", report);

            scheduler.getObject().scheduleJob(
              newJob(reportJob.getClass()).withIdentity(report.getCode()).
                setJobData(dataMap).build(),
              newTrigger().withIdentity(report.getCode()).withSchedule(
                cronSchedule(croneExpression)).build());
            reportJob.runOutOfContext(report, modelService, filePath);
        }
    }

    private ModelService getModelService(final Class aClass) {
        for (final ModelService service : applicationContext.getBeansOfType(
          ModelService.class).values()) {
            if (service.getType().equals(aClass)) {
                return service;
            }
        }
        return null;
    }
}

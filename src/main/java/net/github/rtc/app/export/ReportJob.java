package net.github.rtc.app.export;


import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.service.ReportService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivan Yatcuba on 8/5/14.
 */
@Component
public class ReportJob {
    public static final int TIMER = 5000 ;//cron
//9000000
    @Autowired
    public ReportService reportService;

    @Scheduled(fixedDelay = TIMER)
    public void reportUpdate(){
        List<ReportDetails> myList = reportService.getAll();
        for (ReportDetails report : myList){
            reportService.compileReport(report);
        }
    }
}

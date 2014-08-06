package net.github.rtc.app.export;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by Ivan Yatcuba on 8/5/14.
 */
public class ReportJobFactory {

    public static QuartzJobBean buildJob(ReportDetails reportDetails){
        return new QuartzJobBean() {
            @Override
            protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

            }
        };
    }
}

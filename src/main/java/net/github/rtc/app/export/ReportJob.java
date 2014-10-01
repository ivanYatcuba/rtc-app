package net.github.rtc.app.export;


import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ivan Yatcuba on 8/5/14.
 * Refactored by Vasiliy Sobol on 1/10/14
 */
@Component
public class ReportJob {
    @Autowired
    private ReportService reportService;

    @Scheduled(cron = "0 0/15 * * * ?")
    public void reportUpdate() {
        final List<ReportDetails> myList = reportService.getAll();
        for (ReportDetails report : myList) {
            reportService.compileReport(report);
        }
    }
}

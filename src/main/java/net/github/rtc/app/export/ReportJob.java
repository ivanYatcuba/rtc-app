package net.github.rtc.app.export;


import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportJob {
    @Autowired
    private ReportService reportService;

    @Scheduled(cron = "0 0/15 * * * ?")
    public void reportUpdate() {
        final List<ReportDetails> myList = reportService.findAll();
        for (ReportDetails report : myList) {
            reportService.compileReport(report);
        }
    }
}

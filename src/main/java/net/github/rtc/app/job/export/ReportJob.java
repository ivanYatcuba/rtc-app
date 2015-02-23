package net.github.rtc.app.job.export;


import net.github.rtc.app.model.entity.report.ReportDetails;
import net.github.rtc.app.service.export.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportJob {
    @Autowired
    private ExportService exportService;

    @Scheduled(cron = "0 0/15 * * * ?")
    public void reportUpdate() {
        final List<ReportDetails> myList = exportService.findAll();
        for (ReportDetails report : myList) {
            exportService.compileReport(report);
        }
    }
}

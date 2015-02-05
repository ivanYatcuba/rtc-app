package net.github.rtc.app.service.report;

import net.github.rtc.app.model.entity.report.ReportDetails;
import net.github.rtc.app.service.GenericService;

import java.io.File;

public interface ReportService extends GenericService<ReportDetails> {
    ReportDetails compileReport(ReportDetails report);

    File getReport(ReportDetails details);
}

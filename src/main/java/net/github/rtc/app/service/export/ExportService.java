package net.github.rtc.app.service.export;

import net.github.rtc.app.model.entity.report.ReportDetails;
import net.github.rtc.app.service.generic.GenericService;

import java.io.File;

public interface ExportService extends GenericService<ReportDetails> {
    ReportDetails compileReport(ReportDetails report);

    File getReport(ReportDetails details);
}

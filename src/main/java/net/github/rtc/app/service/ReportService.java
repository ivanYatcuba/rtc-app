package net.github.rtc.app.service;

import net.github.rtc.app.model.report.ReportDetails;

import java.io.File;

public interface ReportService extends GenericService<ReportDetails> {
    ReportDetails compileReport(ReportDetails report);

    File getReport(ReportDetails details);
}

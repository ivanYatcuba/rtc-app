package net.github.rtc.app.service;

import net.github.rtc.app.model.report.ReportDetails;

public interface ReportService extends GenericService<ReportDetails> {
    ReportDetails compileReport(ReportDetails report);
}

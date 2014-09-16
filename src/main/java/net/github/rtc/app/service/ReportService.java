package net.github.rtc.app.service;

import net.github.rtc.app.model.report.ReportDetails;

import java.util.List;

/**
 * Created by Ivan Yatcuba on 8/16/14.
 */
public interface ReportService {
    void insert(ReportDetails report);

    ReportDetails findReportByCode(String code);

    List<ReportDetails> getAll();

    void update(ReportDetails report);

    void delete(ReportDetails report);
}

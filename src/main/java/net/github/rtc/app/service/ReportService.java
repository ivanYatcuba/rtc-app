package net.github.rtc.app.service;

import net.github.rtc.app.model.report.ReportDetails;
import net.github.rtc.app.utils.datatable.search.AbstractSearchCommand;
import net.github.rtc.app.utils.datatable.search.SearchResults;

import java.util.List;

public interface ReportService {
    ReportDetails create(ReportDetails report);

    ReportDetails findByCode(String code);

    List<ReportDetails> findAll();

    void update(ReportDetails report);

    void deleteByCode(String code);

    SearchResults<ReportDetails> search(AbstractSearchCommand searchCommand);

    void compileReport(ReportDetails report);
}

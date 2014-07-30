package net.github.rtc.app.export;

import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ivan Yatcuba on 7/29/14.
 */
public class ExportTask {

    public <T> void exportData(Class<T> aClass, List<T> list, String reportName, String reportPath) {
        XLSXReportBuilder reportBuilder = new XLSXReportBuilder();
        reportBuilder.build(aClass, list, reportName, reportPath);
    }
}

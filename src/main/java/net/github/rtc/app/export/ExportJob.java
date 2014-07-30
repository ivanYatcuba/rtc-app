package net.github.rtc.app.export;

import net.github.rtc.app.service.ModelService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;



/**
 * Created by Ivan Yatcuba on 7/30/14.
 */
public class ExportJob extends QuartzJobBean {

    private Class aClass;
    private ModelService service;
    private String reportName;
    private String reportPath;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ExportTask exportTask = new ExportTask();
        exportTask.exportData(aClass, service.findAll(), reportName, reportPath);
    }

    public Class getaClass() {return aClass;}
    public void setaClass(Class aClass) {this.aClass = aClass;}

    public ModelService getService() {return service;}
    public void setService(ModelService service) {this.service = service;}

    public String getReportName() {return reportName;}
    public void setReportName(String reportName) {this.reportName = reportName;}

    public String getReportPath() {return reportPath;}
    public void setReportPath(String reportPath) {this.reportPath = reportPath;}

}

package net.github.rtc.app.export;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Ivan Yatcuba on 8/5/14.
 */
public class ReportDetails {
    private String name;
    private ExportFormat exportFormat;
    private Class exportClass;
    private List<Field> fields;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public ExportFormat getExportFormat() {return exportFormat;}
    public void setExportFormat(ExportFormat exportFormat) {this.exportFormat = exportFormat;}

    public Class getExportClass() {return exportClass;}
    public void setExportClass(Class exportClass) {this.exportClass = exportClass;}

    public List<Field> getFields() {return fields;}
    public void setFields(List<Field> fields) {this.fields = fields;}
}

package net.github.rtc.app.model.report;


import net.github.rtc.app.utils.ExportFieldExtractor;
import net.github.rtc.util.annotation.Maxlength;
import net.github.rtc.util.annotation.Required;
import net.github.rtc.util.annotation.Validatable;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan Yatcuba on 8/5/14.
 */
@Entity
@Validatable
public class ReportDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @Required
    @Maxlength(50)
    private String name;

    @Column
    private String code;

    @Column
    @Enumerated(EnumType.STRING)
    @Required
    private ExportFormat exportFormat = ExportFormat.XLSX;

    @Column
    @Required
    private Class<? extends Serializable> exportClass;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ReportField", joinColumns =@JoinColumn(name = "report_id"))
    private List<String> fields;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public ExportFormat getExportFormat() {return exportFormat;}
    public void setExportFormat(ExportFormat exportFormat) {this.exportFormat = exportFormat;}

    public Class getExportClass() {return exportClass;}
    public void setExportClass(Class exportClass) {this.exportClass = exportClass;}

    public List<String> getFields() {return fields;}
    public void setFields(List<String> fields) {this.fields = fields;}

    public Date getCreatedDate() {return createdDate;}
    public void setCreatedDate(Date createdDate) {this.createdDate = createdDate;}

    public List<Field> getFieldsFromClass() throws NoSuchFieldException {
       return ExportFieldExtractor.getFieldsFromClass(exportClass, fields);
    }
}

package net.github.rtc.app.model.report;


import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.utils.ExportFieldExtractor;
import net.github.rtc.util.annotation.validation.Maxlength;
import net.github.rtc.util.annotation.validation.Required;
import net.github.rtc.util.annotation.validation.Validatable;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
public class ReportDetails extends AbstractPersistenceObject {
    private static final int PRIMARY_LENGTH = 50;

    @Column
    @Required
    @Maxlength(PRIMARY_LENGTH)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    @Required
    private ExportFormat exportFormat = ExportFormat.XLSX;

    @Column
    @Required
    private Class<? extends Serializable> exportClass;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ReportField",
      joinColumns = @JoinColumn(name = "report_id"))
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 1)
    private List<String> fields;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ExportFormat getExportFormat() {
        return exportFormat;
    }

    public void setExportFormat(final ExportFormat exportFormat) {
        this.exportFormat = exportFormat;
    }

    public Class getExportClass() {
        return exportClass;
    }

    public void setExportClass(final Class exportClass) {
        this.exportClass = exportClass;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(final List<String> fields) {
        this.fields = fields;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<Field> getFieldsFromClass() throws NoSuchFieldException {
        return ExportFieldExtractor.getFieldsFromClass(exportClass, fields);
    }
}

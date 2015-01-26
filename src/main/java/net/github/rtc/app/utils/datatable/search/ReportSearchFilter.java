package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.report.ReportClasses;
import net.github.rtc.app.model.report.ReportDetails;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ReportSearchFilter extends AbstractSearchCommand {

    @Override
    public Order order() {
        return Order.asc("createdDate");
    }

    private static final String STRING_PERCENT = "%";

    private String name;

    private ReportClasses exportClass;

    @Override
    public DetachedCriteria getCriteria() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(ReportDetails.class);
        if (name != null && !("").equals(name)) {
            criteria.add(Restrictions.like("name", STRING_PERCENT + name + STRING_PERCENT));
        }
        if (exportClass != null) {
            criteria.add(Restrictions.eq("exportClass", exportClass));
        }
        return criteria;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReportClasses getExportClass() {
        return exportClass;
    }

    public void setExportClass(ReportClasses exportClass) {
        this.exportClass = exportClass;
    }
}

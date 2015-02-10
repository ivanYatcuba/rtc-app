package net.github.rtc.app.utils.datatable.search.filter;

import net.github.rtc.app.model.entity.report.ReportClasses;
import net.github.rtc.app.model.entity.report.ReportDetails;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ReportSearchFilter extends AbstractSearchCommand {

    private static final String PERCENT = "%";

    private String name;

    private ReportClasses exportClass;

    @Override
    public DetachedCriteria getCriteria() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(ReportDetails.class);
        if (name != null && !("").equals(name)) {
            criteria.add(Restrictions.like("name", PERCENT + name + PERCENT));
        }
        if (exportClass != null) {
            criteria.add(Restrictions.eq("exportClass", exportClass));
        }
        return criteria;
    }

    @Override
    public Order order() {
        return Order.asc("createdDate");
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

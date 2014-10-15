package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.report.ReportDetails;
import org.hibernate.criterion.DetachedCriteria;

/**
 * Created by Berdniky on 15.10.2014.
 */
public class ReportSearchFilter extends AbstractSearchCommand {

    @Override
    public DetachedCriteria getCriteria() {
        return DetachedCriteria.forClass(ReportDetails.class);
    }
}

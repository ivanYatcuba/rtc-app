package net.github.rtc.app.utils.date.impl;

import net.github.rtc.app.utils.date.DateService;
import org.joda.time.*;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component("DateService")
public class DateServiceImpl implements DateService {

    @Override
    public Date getCurrentDate() {
        return DateTime.now().toLocalDateTime().toDate();
    }

    @Override
    public Date addDays(Date oldDate, final int days) {
        return new DateTime(oldDate).plusDays(days).toDate();
    }

    @Override
    public int getMothPeriod(final Date startDate, final Date endDate) {
        final Period period = new Period(new DateTime(startDate), new DateTime(endDate), PeriodType.months());
        return period.getMonths() + 1;
    }
}

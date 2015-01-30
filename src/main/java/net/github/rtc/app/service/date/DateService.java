package net.github.rtc.app.service.date;

import java.util.Date;

/**
 * Created by denis on 13.10.14.
 */
public interface DateService {
    /**
     *
     * @return current Date
     */
    Date getCurrentDate();

    /**
     * This method should add days to existing Date variable
     * @param oldDate date you need to change
     * @param days number of days that would be added
     * @return old date with added days
     */
    Date addDays(Date oldDate, final int days);

    int getMothPeriod(Date startDate, Date endDate);
}

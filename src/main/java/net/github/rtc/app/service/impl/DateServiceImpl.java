package net.github.rtc.app.service.impl;

import net.github.rtc.app.service.DateService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component("DateService")
public class DateServiceImpl implements DateService {
    public Date getCurrentDate() {
        return new Date();
    }
    public Date addDays(Date oldDate, final int days) {
        return new DateTime(oldDate).plusDays(days).toDate();
    }
}

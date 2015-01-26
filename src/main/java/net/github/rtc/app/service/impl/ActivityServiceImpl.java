package net.github.rtc.app.service.impl;


import net.github.rtc.app.dao.ActivityDao;
import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.model.activity.Activity;
import net.github.rtc.app.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends AbstractGenericServiceImpl<Activity> implements ActivityService {
    @Autowired
    private ActivityDao activityDao;

    protected GenericDao<Activity> getDao() {
        return activityDao;
    }
}

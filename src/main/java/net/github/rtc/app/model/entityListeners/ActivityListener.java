package net.github.rtc.app.model.entityListeners;


import net.github.rtc.app.model.activity.IActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PostUpdate;


public class ActivityListener {

//    public void saveData(IActivity activity) {
//
//    }

    private static Logger log = LoggerFactory.getLogger(ActivityListener.class.getName());

    @PostUpdate
    public void updateData(IActivity activity) {
        log.info("hello");
        System.out.println("hello");
        System.out.println(activity.getLogDetail());
    }

//    public void removeData(IActivity activity) {
//
//    }

}

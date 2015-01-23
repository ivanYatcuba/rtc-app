package net.github.rtc.app.model.activity.ActivityAction;

import net.github.rtc.app.model.AbstractEnumTable;

import javax.persistence.*;

/**
 * Created by Alexey Samoylov on 23.01.2015.
 */
@Entity
public class ActivityAction extends AbstractEnumTable<ActivityActionType> {

    public ActivityAction(String name) {
        super(name);
    }

    public ActivityAction(ActivityActionType name) {
        super(name);
    }
}

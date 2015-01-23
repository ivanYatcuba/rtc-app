package net.github.rtc.app.model.activity;

import net.github.rtc.app.model.activity.ActivityAction.ActivityAction;
import net.github.rtc.app.model.activity.ActivityAction.ActivityActionType;
import org.junit.Test;

public class ActivityActionTest {

    @Test
    public void testNewsCreationBa() {
        ActivityAction a = new ActivityAction(ActivityActionType.REMOVED);
        ActivityAction b = new ActivityAction("Saved");
        try {
            ActivityAction c = new ActivityAction("dasdasd"); //error
        } catch (Exception e) {  System.out.println(e);}
    }



}

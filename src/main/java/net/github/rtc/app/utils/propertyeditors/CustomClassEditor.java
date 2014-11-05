package net.github.rtc.app.utils.propertyeditors;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.user.User;

import java.beans.PropertyEditorSupport;

/**
 * Created by Berdniky on 05.11.2014.
 */
public class CustomClassEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(final String text) {
        if (("User").equals(text)) {
            this.setValue(User.class);
        } else {
            this.setValue(Course.class);
        }

    }
}

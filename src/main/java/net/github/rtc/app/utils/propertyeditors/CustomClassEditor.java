package net.github.rtc.app.utils.propertyeditors;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.user.User;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

public class CustomClassEditor extends PropertyEditorSupport {

    private static Map<String, Class> classMap = new HashMap<>();
    static {
        classMap.put(Course.class.getSimpleName(), Course.class);
        classMap.put(User.class.getSimpleName(), User.class);
    }

    @Override
    public void setAsText(final String text) {
       this.setValue(classMap.get(text));
    }

    @Override
    public String getAsText() {
        if(this.getValue() != null) {
            return ((Class)this.getValue()).getSimpleName();
        }
        return super.getAsText();
    }
}

package net.github.rtc.app.utils.propertyeditors;

import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.user.User;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(JUnit4ClassRunner.class)
public class CustomClassEditorTest {
    private CustomClassEditor customClassEditor = new CustomClassEditor();

    @Test
    public void checkSetAsTextUser(){
        customClassEditor.setAsText("User");
        assertEquals(User.class, customClassEditor.getValue());
    }

    @Test
    public void checkSetAsTextCourse(){
        customClassEditor.setAsText("Course");
        assertEquals(Course.class, customClassEditor.getValue());
    }
}

package net.github.rtc.app.utils.propertyeditors;

import net.github.rtc.app.model.course.Tag;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

@RunWith(JUnit4ClassRunner.class)
public class CustomTagsEditorTest {

    private static final String TEST_STRING = "X,Y,Z,";
    private CustomTagsEditor customTagsEditor;

    @Before
    public void setUp() {
        customTagsEditor = new CustomTagsEditor();
    }

    @Test
    public void getAsTextTest() {
        customTagsEditor.setValue(Arrays.asList(new Tag("X"), new Tag("Y"), new Tag("Z")));
        assertEquals(TEST_STRING, customTagsEditor.getAsText());
    }

    @Test
    public void setAsTextTest() {
        customTagsEditor.setAsText(TEST_STRING);
        List<Tag> tags =  Arrays.asList(new Tag("X"), new Tag("Y"), new Tag("Z"));
        assertEquals(tags, customTagsEditor.getValue());
    }
}

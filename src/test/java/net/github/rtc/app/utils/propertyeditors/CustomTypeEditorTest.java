package net.github.rtc.app.utils.propertyeditors;


import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

public class CustomTypeEditorTest {
    private static final String TEST_STRING = "X,Y,Z,";
    private CustomTypeEditor customTagsEditor;

    @Before
    public void setUp() {
        customTagsEditor = new CustomTypeEditor();
    }

    @Test
    public void getAsTextTest() {
        customTagsEditor.setValue(Arrays.asList("X", "Y", "Z"));
        assertEquals(TEST_STRING, customTagsEditor.getAsText());
    }

    @Test
    public void setAsTextTest() {
        customTagsEditor.setAsText(TEST_STRING);
        Iterator<String> iterator = ((Set<String>)customTagsEditor.getValue()).iterator();
        assertEquals("Y", iterator.next());
        assertEquals("X", iterator.next());
        assertEquals("Z", iterator.next());
    }
}

package net.github.rtc.app.utils.propertyeditors;


import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

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
        assertTrue(((Set<String>)customTagsEditor.getValue()).containsAll(Arrays.asList("X", "Y", "Z")));
    }
}

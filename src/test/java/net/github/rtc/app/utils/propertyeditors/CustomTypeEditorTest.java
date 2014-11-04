package net.github.rtc.app.utils.propertyeditors;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        List<String> strings = Arrays.asList( "Y", "X", "Z");
        assertEquals(strings, new ArrayList<String>((Set)customTagsEditor.getValue()));
    }
}

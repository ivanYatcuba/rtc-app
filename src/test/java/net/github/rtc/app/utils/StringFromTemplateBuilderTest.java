package net.github.rtc.app.utils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class StringFromTemplateBuilderTest {

    private static final String TEMPLATE_PATH = "/templates/testTemplate.ftl";
    private static final String STRING_TEMPLATE = "This is test string with params: %s, %s";

    private static final String UNKNOWN_VALUE = "UNKNOWN VALUE";

    @Test
    public void testStringFromTemplateBuilder() {
        final Map<String, Object> templateParams = new HashMap<>();
        templateParams.put("param1", "Some String");
        templateParams.put("param2", 5);
        StringFromTemplateBuilder builder = new StringFromTemplateBuilder();
        builder.setTemplate(TEMPLATE_PATH).setTemplateParams(templateParams);
        assertEquals(String.format(STRING_TEMPLATE, "Some String", 5), builder.build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongPath(){
        new StringFromTemplateBuilder().setTemplate("/templates/notExists.ftl");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongPathNull(){
        new StringFromTemplateBuilder().setTemplate(null);
    }

    @Test
    public void testWrongArgumentMap(){
        final Map<String, Object> templateParams = new HashMap<>();
        templateParams.put("paramNotExists", "Some String");
        assertEquals(UNKNOWN_VALUE,
                new StringFromTemplateBuilder().setTemplate(TEMPLATE_PATH).setTemplateParams(templateParams).build());
        }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongArgumentMapNull(){
        new StringFromTemplateBuilder().setTemplateParams(null);
    }
}

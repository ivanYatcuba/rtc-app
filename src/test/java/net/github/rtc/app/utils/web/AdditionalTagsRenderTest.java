package net.github.rtc.app.utils.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.support.BindStatus;
import org.springframework.web.servlet.support.RequestContext;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(BlockJUnit4ClassRunner.class)
public class AdditionalTagsRenderTest {

    @InjectMocks
    private AdditionalTagsRenderer tagsRenderer;
    @Mock
    private BindStatus bindStatus;
    @Mock
    private Map<String, Object> model;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRender() {
        final String beanName = "beanName.";
        when(bindStatus.getPath()).thenReturn(beanName);
        when(bindStatus.getExpression()).thenReturn(new String());
        when(model.get(anyString())).thenReturn(new String());
        assertEquals("", tagsRenderer.renderAdditionalTags(bindStatus, model));
    }
}

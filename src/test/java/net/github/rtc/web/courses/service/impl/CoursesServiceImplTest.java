package net.github.rtc.web.courses.service.impl;

import net.github.rtc.web.courses.resource.CoursesResource;
import net.github.rtc.web.courses.resource.impl.CoursesResourceImpl;
import net.github.rtc.web.courses.model.Author;
import net.github.rtc.web.courses.model.Courses;
import net.github.rtc.web.courses.service.CoursesService;
import net.github.rtc.web.exception.ServiceProcessingException;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Vladislav Pikus
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
public class CoursesServiceImplTest {

    @Autowired
    private CoursesService service;
    private CoursesResource mockResource;

    private final String code = "fcb56955-5344-41e4-897b-d69387e5fa55";

    private Courses course;

    @Before
    public void setUp() throws Exception {
        mockResource = mock(CoursesResourceImpl.class);
        ((CoursesServiceImpl)service).setResource(mockResource);
        course = new Courses("codeTest", "nameTest", "DEV", new Author("Vasya", "Pupkin", "vasia@gmail.com"), DateTime.now().toDate(), DateTime.now().toDate());
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(code);
        verify(mockResource).delete(code);
    }

    @Test(expected = ServiceProcessingException.class)
    public void testDeleteWithNullId() throws Exception {
        service.delete(null);
    }
}

package com.springapp.mvc.courses.resource.impl;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.springapp.mvc.courses.model.Courses;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Vladislav Pikus
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
public class CoursesResourceImplTest {

    @Autowired
    private CoursesResourceImpl resource;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        resource.setRestTemplate(restTemplate);

    }

    private String loadXmlFile(final String filename) throws IOException {
        URL url = Resources.getResource(filename);
        return Resources.toString(url, Charsets.UTF_8);
    }

    @Test
    public void testFindById() throws Exception {
        final String responseXml = loadXmlFile("course.json");
        final Integer id = 5;
        mockServer.expect(requestTo(resource.getHostUrl() + "courses/" + id))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseXml, MediaType.APPLICATION_JSON));
        final Courses courses = resource.findById(id);
        mockServer.verify();
        assertEquals(id, courses.getId());

    }

    @Test
    public void testFindAll() throws Exception {
        final String responseXml = loadXmlFile("courses.json");
        mockServer.expect(requestTo(resource.getHostUrl() + "courses"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseXml, MediaType.APPLICATION_JSON));
        Collection<Courses> courses = resource.findAll();
        mockServer.verify();
        assertNotNull(courses);
        assertTrue(courses.size() == 2);
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = 5;
        mockServer.expect(requestTo(resource.getHostUrl() + "courses/" + id))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withSuccess());
        resource.delete(id);
        mockServer.verify();
    }
}

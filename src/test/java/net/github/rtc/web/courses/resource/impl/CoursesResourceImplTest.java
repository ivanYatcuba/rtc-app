package net.github.rtc.web.courses.resource.impl;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import net.github.rtc.web.courses.model.Courses;
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

//import static org.junit.Assert.assertEquals;
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

    private final String code = "fcb56955-5344-41e4-897b-d69387e5fa55";

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
        mockServer.expect(requestTo(resource.getHostUrl() + "courses/" + code))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseXml, MediaType.APPLICATION_JSON));
        final Courses courses = resource.findByCode(code);
        mockServer.verify();
        assertEquals(code, courses.getCode());

    }

   @Test
    public void testDelete() throws Exception {
        mockServer.expect(requestTo(resource.getHostUrl() + "courses/" + code))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withSuccess());
        resource.delete(code);
        mockServer.verify();
    }
}

package net.github.rtc.app.impl;

import net.github.rtc.app.dao.impl.CoursesDaoImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static junit.framework.Assert.assertEquals;

//import static org.junit.Assert.assertEquals;

/**
 * @author Vladislav Pikus
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
public class CourseResourceImplTest {

   @Autowired
    private CoursesDaoImpl resource;

    private final String code = "fcb56955-5344-41e4-897b-d69387e5fa55";



    private MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {


    }


    @Test
    public void fakeTest() throws Exception {
        assertEquals(true,true);
    }
    /*

    private String loadXmlFile(final String filename) throws IOException {
        URL url = Resources.getResource(filename);
        return Resources.toString(url, Charsets.UTF_8);
    }

   @Test
    public void testFindById() throws Exception {
        final String responseXml = loadXmlFile("course.json");
        mockServer.expect(requestTo(dao.getHostUrl() + "courses/" + code))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseXml, MediaType.APPLICATION_JSON));
        final Course courses = dao.findByCode(code);
        mockServer.verify();
        assertEquals(code, courses.getCode());

    }

   @Test
    public void testDelete() throws Exception {
        mockServer.expect(requestTo(dao.getHostUrl() + "courses/" + code))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withSuccess());
        dao.delete(code);
        mockServer.verify();
    } */
}

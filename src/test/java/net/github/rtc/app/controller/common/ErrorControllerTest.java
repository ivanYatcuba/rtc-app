package net.github.rtc.app.controller.common;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
public class ErrorControllerTest {

    private static final String STRING_JAVAX = "javax";
    private static final String STRING_DOT_SERVLET = ".servlet";

    @InjectMocks
    private ErrorController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testErrorPage500() throws Exception {
        mockMvc.perform(get("/error500").requestAttr(STRING_JAVAX + STRING_DOT_SERVLET
                + ".error.status_code", new Integer(500)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("errorTitle", "errorMessage", "errorCause"));
    }

    @Test
    public void testErrorPage404() throws Exception {
        mockMvc.perform(get("/error404").requestAttr(STRING_JAVAX + STRING_DOT_SERVLET
                + ".error.status_code", new Integer(500)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("errorTitle", "errorMessage", "errorCause"));

    }

}

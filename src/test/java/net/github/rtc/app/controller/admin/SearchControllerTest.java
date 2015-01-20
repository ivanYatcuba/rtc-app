package net.github.rtc.app.controller.admin;


import net.github.rtc.app.model.activity.Activity;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.*;
import net.github.rtc.app.utils.datatable.search.ActivitySearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = "classpath:mvc-test.xml")
public class SearchControllerTest {

    @Mock
    private NewsService newsService;
    @Mock
    private CourseService courseService;
    @Mock
    private UserService userService;
    @Mock
    private ReportService reportService;
    @Mock
    private ActivityService activityService;
    @InjectMocks
    private SearchController searchController;
    private MockMvc mockMvc;

    @Before
    public void prepareData() {
        MockitoAnnotations.initMocks(this);
        Authentication auth = new UsernamePasswordAuthenticationToken(new User(),null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        this.mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }
    @Ignore
    @Test
    public void checkActivityTable() throws Exception {
        ActivitySearchFilter searchFilter = new ActivitySearchFilter();
        searchFilter.setPage(1);
        searchFilter.setPerPage(5);
        searchFilter.setDateMoreLessEq('=');
        searchFilter.setUser("");
        mockMvc.perform(post("/admin/activityTable").sessionAttr("activityFilter", searchFilter))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("activities"));
    }
}

package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.NewsService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.datatable.search.NewsSearchFilter;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
public class NewsControllerTest {
    @Mock
    private NewsService newsService;
    @Mock
    private UserService userService;
    private Date FORMER_ENTRY_CREATION_DATE = new Date(1);
    private Date LATTER_ENTRY_CREATION_DATE = new Date(2);
    private List<News> newsList;
    @InjectMocks
    private NewsController newsController;
    private MockMvc mockMvc;

    @Before
    public void prepareNewsList() {
        MockitoAnnotations.initMocks(this);
        News news1 = new News();
        news1.setTitle("title1");
        news1.setCreateDate(FORMER_ENTRY_CREATION_DATE);
        News news2 = new News();
        news2.setTitle("title2");
        news2.setCreateDate(LATTER_ENTRY_CREATION_DATE);
        newsList = Arrays.asList(news1, news2);
        Authentication auth = new UsernamePasswordAuthenticationToken(new User(),null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        this.mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
    }

    @Test
    public void returnViewWithNews() throws Exception {
        SearchResults<News> results = new SearchResults<>();
        results.setResults(newsList);
        when(newsService.search((NewsSearchFilter)notNull())).thenReturn(results);
        mockMvc.perform(get("/admin/news/feed"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("news", newsList))
                .andExpect(model().attributeExists("lastUpdate"));
        verify(newsService, times(1)).search((NewsSearchFilter)notNull());
        verifyNoMoreInteractions(newsService);
    }
}

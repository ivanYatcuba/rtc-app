package net.github.rtc.app.controller.admin;

import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import net.github.rtc.app.utils.datatable.search.UserSearchFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Berdniky on 18.11.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mvc-test.xml"})
@WebAppConfiguration
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;

    private List<User> userList;
    @Mock
    private UserService userService;

    @Before
    public void prepareUsersList(){
        MockitoAnnotations.initMocks(this);
        Authentication auth = new UsernamePasswordAuthenticationToken(new User(), null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void returnViewWithUsers() throws Exception {
        User user1 = new User();
            user1.setName("TestName1");
            user1.setSurname("TestSurname1");
            user1.setEmail("TestEmail1");
        User user2 = new User();
            user2.setName("TestName2");
            user2.setSurname("TestSurname2");
            user2.setEmail("TestEmail2");
        userList = Arrays.asList(user1, user2);
        SearchResults<User> resultUserList = new SearchResults<>();
        resultUserList.setResults(userList);

        when(userService.search((UserSearchFilter)notNull())).thenReturn(resultUserList);

        mockMvc.perform(get("/admin/user/viewAll"))
                .andExpect(status().isOk());
    }

}

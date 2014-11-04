package net.github.rtc.app.utils.propertyeditors;

import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(JUnit4ClassRunner.class)
public class CustomExpertsEditorTest {
    @Mock
    private UserService userService;
    private User user1;
    private User user2;
    private String testString;
    private CustomExpertsEditor customExpertsEditor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user1 = new User();
        user1.setCode("X");

        user2 = new User();
        user2.setCode("Y");
        testString = "X,Y";
        customExpertsEditor = new CustomExpertsEditor(userService);
    }

    @Test
    public void testSetAsText() {
        when(userService.findByCode("X")).thenReturn(user1);
        when(userService.findByCode("Y")).thenReturn(user2);
        customExpertsEditor.setAsText(testString);
        List<User> users = new ArrayList<>((Set<User>) customExpertsEditor.getValue());
        assertEquals(2, users.size());
        assertEquals("Y", users.get(0).getCode());
        assertEquals("X", users.get(1).getCode());
    }
}

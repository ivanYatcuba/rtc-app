package net.github.rtc.app.utils.propertyeditors;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(JUnit4ClassRunner.class)
public class CustomRoleEditorTest {
    @Mock
    private UserService userService;
    private Role role1;
    private Role role2;
    private String testString;
    private CustomRoleEditor customRoleEditor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        role1 = new Role(RoleType.ROLE_ADMIN);
        role2 = new Role(RoleType.ROLE_EXPERT);

        testString = "ROLE_ADMIN,ROLE_EXPERT";

        customRoleEditor = new CustomRoleEditor(userService);
    }

    @Test
    public void testSetAsText() {
        when(userService.getRoleByType(RoleType.ROLE_ADMIN)).thenReturn(role1);
        when(userService.getRoleByType(RoleType.ROLE_EXPERT)).thenReturn(role2);
        customRoleEditor.setAsText(testString);
        List<Role> roles = (List<Role>)customRoleEditor.getValue();
        assertEquals(2, roles.size());
        assertEquals(RoleType.ROLE_ADMIN, roles.get(0).getName());
        assertEquals(RoleType.ROLE_EXPERT, roles.get(1).getName());
    }
}

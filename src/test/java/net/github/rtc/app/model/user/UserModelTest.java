package net.github.rtc.app.model.user;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4ClassRunner.class)
public class UserModelTest {

    @Test
    public void userHasRole() {
        User user = new User();
        user.setAuthorities(Arrays.asList(new Role(RoleType.ROLE_ADMIN)));
        assertTrue(user.hasRole(RoleType.ROLE_ADMIN.name()));
        assertTrue(!user.hasRole(RoleType.ROLE_EXPERT.name()));
    }

    @Test
    public void toStringTest() {
        User user = new User();
        user.setName("X");
        user.setSurname("Y");
        String expect = "X" + " " + "Y";
        assertTrue(expect.equals(user.toString()));
    }

    @Test
    public void isActiveTest() {
        User user = new User();
        user.setStatus(UserStatus.ACTIVE);
        assertTrue(user.isActive());
    }

    @Test
    public void isForRemovalTest() {
        User user = new User();
        user.setStatus(UserStatus.FOR_REMOVAL);
        assertTrue(user.isForRemoval());
    }

    @Test
    public void toShortStringTest() {
        User user = new User();
        user.setName("X");
        user.setSurname("Y");
        user.setEmail("Z");
        String expect = "X" + " " + "Y" + " " + "Z";
        assertTrue(expect.equals(user.shortString()));
    }

    @Test
    public void roleFindAllTest(){
        List<String> res = RoleType.findAll();
        assertEquals(RoleType.values().length, res.size());
        for (final RoleType type: RoleType.values()) {
            assertTrue(res.contains(type.name()));
        }
    }

    @Test
    public void roleGetTypeByStringTest(){
        assertNull(RoleType.getTypeByString("X"));
        assertEquals(RoleType.ROLE_ADMIN, RoleType.getTypeByString("ROLE_ADMIN"));
    }

    @Test
    public void roleToStringTest() {
        Role role = new Role();
        role.setName(RoleType.ROLE_ADMIN);
        assertEquals(RoleType.ROLE_ADMIN.name(), role.toString());
    }
}

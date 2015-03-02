package net.github.rtc.app.service.security;

import net.github.rtc.app.model.entity.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static junit.framework.Assert.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class AuthorizedUserProviderTest {

    final User authorizedUser = new User();

    @Before
    public void prepareUsersList(){
        authorizedUser.setName("NAME");
        authorizedUser.setSurname("SURNAME");
        authorizedUser.setEmail("email@email.com");
        authorizedUser.setCode("CODE");
        Authentication auth = new UsernamePasswordAuthenticationToken(authorizedUser, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void getAuthorizedUserTest() {
        User user = AuthorizedUserProvider.getAuthorizedUser();
        assertEquals(authorizedUser.getName(), user.getName());
        assertEquals(authorizedUser.getSurname(),user.getSurname());
        assertEquals(authorizedUser.getEmail(),user.getEmail());
        assertEquals(authorizedUser.getCode(),user.getCode());

    }

    @Test
    public void getAuthorizedUserNameTest() {
        assertEquals(authorizedUser.getUsername(), AuthorizedUserProvider.getAuthorizedUserName());
    }

}

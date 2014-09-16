package net.github.rtc.app.service.security;

import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication
        .UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserServiceLogin userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws
            AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        if (username == null || username.trim().isEmpty()) {
            throw new BadCredentialsException("Email cannot be empty");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new BadCredentialsException("Password cannot be empty");
        }

        User user = userService.loadUserByUsername(username);

        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user
                .getAuthorities();

        return new UsernamePasswordAuthenticationToken(username, password,
                authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

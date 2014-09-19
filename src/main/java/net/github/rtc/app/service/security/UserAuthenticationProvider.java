package net.github.rtc.app.service.security;

import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserStatus;
import net.github.rtc.app.service.UserServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication
  .UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public Authentication authenticate(final Authentication authentication) {
        final String username = authentication.getName();
        final String password = (String) authentication.getCredentials();

        if (username == null || username.trim().isEmpty()) {
            throw new BadCredentialsException("Email cannot be empty");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new BadCredentialsException("Password cannot be empty");
        }

        final User user = userService.loadUserByUsername(username);

        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }

        if (user.getStatus() == UserStatus.FOR_REMOVAL) {
            throw new BadCredentialsException(
                    "Contact the administrator tatyana.bulanaya@gmail.com");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        final Collection<? extends GrantedAuthority> authorities
          = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(username, password,
          authorities);
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return true;
    }
}

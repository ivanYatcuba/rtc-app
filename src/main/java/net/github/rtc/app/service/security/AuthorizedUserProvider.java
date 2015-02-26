package net.github.rtc.app.service.security;

import net.github.rtc.app.model.entity.user.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class AuthorizedUserProvider {


    final private static String ANONYMOUS = "anonymousUser";

    private AuthorizedUserProvider() { }

    /**
     * Receive User-object of current authorized user
     * @return User object of current authorized user
     */
    static public User getAuthorizedUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return new User(ANONYMOUS, ANONYMOUS, ANONYMOUS, ANONYMOUS + "@" + ANONYMOUS + "." + ANONYMOUS, ANONYMOUS);
        } else {
            return (User) authentication.getPrincipal();
        }
    }

    /**
     * Receive current authorized user name
     * @return string username of authorized user
     */
    static public String getAuthorizedUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

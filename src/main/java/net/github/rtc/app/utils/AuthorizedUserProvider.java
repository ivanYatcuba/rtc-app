package net.github.rtc.app.utils;

import net.github.rtc.app.model.entity.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

public final class AuthorizedUserProvider {
    private AuthorizedUserProvider() { }

    static public User getAuthorizedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    static public String getAuthorizedUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

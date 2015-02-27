package net.github.rtc.app.service.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class SimpleAuthenticationSuccessHandler implements
  AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * Called when a user has been successfully authenticated.
     * @param request - the request which caused the successful authentication
     * @param response - the response
     * @param authentication - the Authentication object which was created during the authentication process
     */
    @Override
    public void onAuthenticationSuccess(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    /**
     * Handle request and redirect to some url
     * @param request - the request which caused the successful authentication
     * @param response - the response
     * @param authentication - the Authentication object which was created during the authentication process
     */
    protected void handle(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Builds the target URL according to the logic defined in the main class
     * Javadoc.
     */
    protected String determineTargetUrl(final Authentication authentication) {
        final Collection<? extends GrantedAuthority> authorities
          = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            if (("ROLE_ADMIN").equals(grantedAuthority.getAuthority())) {
                return "/admin";
            } else {
                return "/user/profile/";
            }
        }
        return "/";
    }

    /**
     * Remove attributes from session which have got from request
     * @param request - the request which caused the successful authentication
     */
    protected void clearAuthenticationAttributes(
      final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    /**
     * Set redirect strategy field
     * @param redirectStrategy - Redirect strategy object
     */
    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    /**
     * Get current redirect strategy
     * @return  - redirect strategy object
     */
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}

package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.dto.user.ProfileHeaderDTO;
import net.github.rtc.app.utils.AuthorizedUserProvider;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages = { "net.github.rtc.app.controller.expert", "net.github.rtc.app.controller.user" })
public class ProfileHeaderInitializer {
    @ModelAttribute("profileHeader")
    public ProfileHeaderDTO getProfileHeaderDTO() {
        return AuthorizedUserProvider.getProfileHeaderDTO();
    }
}
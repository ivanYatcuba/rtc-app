package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.dto.user.ProfileHeaderDto;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.message.MessageService;
import net.github.rtc.app.service.security.AuthorizedUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages = { "net.github.rtc.app.controller.expert", "net.github.rtc.app.controller.user" })
public class ProfileHeaderInitializer {
    @Autowired
    private MessageService messageService;

    @ModelAttribute("profileHeader")
    public ProfileHeaderDto getProfileHeaderDto() {
        final User user =  AuthorizedUserProvider.getAuthorizedUser();
        final int unreadMessageCount =  messageService.getUserUnreadMessageCount(user.getCode());
        return new ProfileHeaderDto(user.getSurnameName(), user.getPhoto(), unreadMessageCount);
    }
}
package net.github.rtc.app.model.dto.builder;

import net.github.rtc.app.model.dto.user.MessageDTO;
import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MessageDTOBuilder {

    @Autowired
    private UserService userService;

    private Message source;

    public MessageDTOBuilder(Message source) {
        this.source = source;
    }

    public MessageDTO build() {
        final String userCode = source.getSenderUserCode();
        final String userName =   userService.findByCode(userCode).getFullName();

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUserName(userName);
        messageDTO.setText(source.getText());
        messageDTO.setSendingDate(source.getSendingDate());
        return messageDTO;
    }
}

package net.github.rtc.app.service.message;

import net.github.rtc.app.model.dto.user.MessageDTO;
import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.service.generic.GenericService;
import net.github.rtc.app.model.dto.SearchResults;
import net.github.rtc.app.model.dto.filter.MessageSearchFilter;

public interface MessageService extends GenericService<Message> {

    SearchResults<MessageDTO> searchMessagesForUser(MessageSearchFilter searchFilter);
    Message readMessage(String messageCode);
    int getUserUnreadMessageCount(String userCode);
}

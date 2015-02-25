package net.github.rtc.app.service.message;

import net.github.rtc.app.model.dto.user.MessageDto;
import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.service.generic.GenericService;
import net.github.rtc.app.model.dto.SearchResults;
import net.github.rtc.app.model.dto.filter.MessageSearchFilter;

/**
 * Service that is responsible for sending messages from system or other users
 * on user's account and operation on Message model class
 * @see net.github.rtc.app.model.entity.message.Message
 */
public interface MessageService extends GenericService<Message> {

    /**
     * Search messages for user that is presented as DTOs
     * @param searchFilter object that contains search params
     * @see net.github.rtc.app.model.dto.filter.MessageSearchFilter
     *
     * @return search results for user view presented as DTO
     * @see net.github.rtc.app.model.dto.user.MessageDto
     */
    SearchResults<MessageDto> searchMessagesForUser(MessageSearchFilter searchFilter);

    /**
     * If message has isRead property false set it to true
     * @see net.github.rtc.app.model.entity.message.Message
     * @param messageCode code of the message for what operation will be performed
     * @return updated message
     */
    Message readMessage(String messageCode);

    /**
     * Get how much unread messages user have
     * @param userCode code of user for what operation will be performed
     * @return count of unread messages
     */
    int getUserUnreadMessageCount(String userCode);
}

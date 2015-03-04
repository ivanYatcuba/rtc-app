package net.github.rtc.app.dao.message;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.model.entity.message.Message;

import javax.annotation.Resource;

/**
 * Data access object for Message domain class
 * @see net.github.rtc.app.model.entity.message.Message
 */
@Resource
public interface MessageDao  extends GenericDao<Message> {

    /**
     * Get count of  user messages with isRead = false
     * @param userCode code of user for what messages needs to be found
     * @return count of unread messages
     */
    int getUnreadMessageCount(String userCode);
}
